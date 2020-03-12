function desenharGraficos() {

    var tabela = new google.visualization.DataTable();
    tabela.addColumn('string', 'categorias');
    tabela.addColumn('number', 'valores');
    tabela.addRows([

        ['Educação', 2000],
        ['Transporte', 500],
        ['Lazer', 230],
        ['Saúde', 50],
        ['Cartão de crédito', 900],
        ['Alimentação', 260]
    ]);

    var opcoes = {
        'title': 'Tipos de Gastos',
        'height': 400,
        'width': 800,
        'pieHole': 0.4,
        'is3D': true,
        'legend': 'labeled',
        'pieSliceText': 'value',
        //'colors': ['red','grey', 'yellow', 'blue', 'pink', 'purple']
        'slices': {
            0: {},
            1: { color: 'gray' },
            2: { color: '#a6a6a6' },
            3: { color: 'gray' },
            4: { offset: 0.2 },
            5: { color: 'gray' }
        }

    };

    var grafico = new google.visualization.PieChart(document.getElementById('graficoPizza'));
    grafico.draw(tabela, opcoes)

    tabela = new google.visualization.DataTable();
    tabela.addColumn('string', 'mês');
    tabela.addColumn('number', 'gastos');
    tabela.addRows([
        ['jan', 800],
        ['fev', 400],
        ['mar', 1100],
        ['abr', 400],
        ['mai', 500],
        ['jun', 750],
        ['jul', 1500],
        ['ago', 650],
        ['set', 850],
        ['out', 400],
        ['nov', 1000],
        ['dez', 720]
    ]);

    var opcoes = {
        'title': 'Gastos por mês',
        'width': 650,
        'height': 300,
        legend: 'none',
        'vAxis': {
            format: 'currency',
            gridlines: { color: "transparent" }
        },
        curveType: 'function'
    }

    var grafico = new google.visualization.LineChart(document.getElementById('graficoLinha'));
    grafico.draw(tabela, opcoes);


    var tabela = google.visualization.arrayToDataTable(
        [
            ['Mês', 'Entrada', 'Saída'],
            ['jan', 2500, 1000],
            ['fev', 1000, 500],
            ['mar', 3000, 1300],
            ['abr', 1500, 1700],
            ['mai', 5000, 2250],
            ['jun', 3567, 3000],
            ['jul', 3452, 1468],
            ['ago', 1833, 5250],
            ['set', 1800, 1000],
            ['out', 1800, 1000],
            ['nov', 3569, 1500],
            ['dez', 3000, 1740]
        ]);

    var opcoes = {

        title: 'Entradas e saídas da conta',
        width: 800,
        height: 400,
        vAxis: {
            gridlines: { color: 'transparent' },
            format: 'currency',
        },
    }

    var grafico = new google.visualization.ColumnChart(document.getElementById('graficoColuna'));
    grafico.draw(tabela, opcoes);

    var tabela = new google.visualization.DataTable();
    tabela.addColumn('string', 'categorias');
    tabela.addColumn('number', 'valores');
    tabela.addColumn({ type: 'number', role: 'annotation' });
    tabela.addColumn({ type: 'string', role: 'style' });
    tabela.addRows([

        ['Educação', 2000, 2000, 'blue'],
        ['Transporte', 500, 500, 'grey'],
        ['Lazer', 230, 230, 'grey'],
        ['Saúde', 50, 50, 'grey'],
        ['Cartão de crédito', 900, 900, '#8904B1'],
        ['Alimentação', 260, 260, 'grey']
    ]);

    tabela.sort([{ column: 1, desc: true }]);

    var formatter = new google.visualization.NumberFormat(
        { prefix: 'R$' });
    formatter.format(tabela, 2);

    var opcoes = {
        title: 'Tipos de Gastos',
        height: 400,
        width: 800,
        vAxis: { gridlines: { count: 0 } },
        hAxis: { gridlines: { count: 0 }, textPosition: 'none' },
        legend: 'none',
        annotations: { alwaysOutside: true }
    }

    var grafico = new google.visualization.BarChart(document.getElementById('graficoColunaSurpresa'));
    grafico.draw(tabela, opcoes);


    var dadosJson = $.ajax({
        url: 'https://gist.githubusercontent.com/Diego-Gomes/2f5559f3df0c804e0f062ed8c7372bc2/raw/b36383907a047469f9f2d7a13511f2d5ab96b8c8/dados.json',
        mimeType: 'application-json',
        dataType: 'json',
        async: false,
    }).responseText;

    var tabela = new google.visualization.DataTable(dadosJson);
    tabela.sort([{ column: 1, desc: true }]);
    var formatter = new google.visualization.NumberFormat(
        { prefix: 'R$' });
    formatter.format(tabela, 2);

    var opcoes = {
        title: 'Tipos de Gastos',
        height: 400,
        width: 800,
        vAxis: { gridlines: { count: 0 } },
        hAxis: { gridlines: { count: 0 }, textPosition: 'none' },
        legend: 'none',
        annotations: { alwaysOutside: true }
    }

    var grafico = new google.visualization.BarChart(
        document.getElementById('graficoBarrasJson'));

    grafico.draw(tabela, opcoes);
}