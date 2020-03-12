function desenhar() {
	
	var dadosJson = consultarDados();
	
	var table = gerarTabela(dadosJson);
	
	var tabelaAgrupada = agruparTabelaPorHorarioETrade(table);
	
	var tabelaPersonalizada = personalizarTabela(tabelaAgrupada);
	
	var opcoes = {
			isStacked: true,
			//title: 'Quantidade negociações por hora',
			'width':1000,
			'height':200,
			titleTextStyle: {
								color: '#5e5851',
								fontSize: 20,
								fontType: 'Arial'
			},
			series: {
				0: {color:'#3682ff'},
				1: {color:'#a6a6a6'}
			},
			vAxis: {
						gridlines: {
										count:4,
									//	color: 'transparent'
						}
			},
			hAxis: {
				gridlines: {
								count:3,
								//color: 'transparent'
				}
			},
			legend: {
					textStyle: {
									color: '#928e8a',
									fontSize: 16
					}	
			},
			charArea: {
				'width':500,
				'height':100,
			}

		}

	$( "#btnSee" ).html( "See" )
	var btn = document
			.getElementById('btnSee');
	//btn.innerHtml = "See";
	
	var grafico = new google.visualization.AreaChart(document
			.getElementById('grafico'));
	grafico.draw(tabelaPersonalizada, opcoes);

}

function consultarDados() {

	var resultado = $.ajax({
		url : 'http://localhost:8080/bitcoin/',
		dataType : 'json',
		async : false,
	}).responseJSON;

	return resultado;
}

function gerarTabela(dadosJson) {

	var _nameColumType = 'type';
	var _nameColumAmount = 'amount';
	var _nameColumDate = 'date';

	var table = new google.visualization.DataTable();

	table.addColumn('date', _nameColumDate);
	table.addColumn('string', _nameColumType);
	table.addColumn('number', _nameColumAmount);

	dadosJson.forEach(carregarLinhas);

	function carregarLinhas(item, index) {

		table.addRow([ new Date(item[_nameColumDate]) , item[_nameColumType], item[_nameColumAmount]]);

	};

	return table;
}

function agruparTabela(dt) {

	var result = google.visualization.data.group(dt, [ 0 ], [ {
		'column' : 2,
		'aggregation' : google.visualization.data.sum,
		'type' : 'number'
	} ]);

	return result;

}

function agruparTabelaPorHorarioETrade(dt) {

	var result = google.visualization.data.group(dt, 
		[ 	
			
			{
				'column' : 0,
				'label' : 'Horário',
				'modifier' : obterHoras,
				'type' : 'number'
			},
			
			{
				'column' : 1,
				'label' : 'Tipo',
				'type' : 'string'
			}
		], 
		[ 
		{
			'column' : 2,
			'label' : 'Soma',
			'aggregation' : google.visualization.data.count,
			'type' : 'number'
		} 
		]
	);
	

	return result;

}

function obterHoras(date) {
	return date.getHours();
}

function personalizarTabela(tableAgrupada){
	
	var table = new google.visualization.DataTable();

	table.addColumn('number', 'hora');
	table.addColumn('number', 'sell');
	table.addColumn('number', 'buy');
	
	tableAgrupada.wg.forEach(gerar);
	
	function gerar(item, index){
		var horas  	= item['c'][0]['v'];
		var valor 	= item['c'][2]['v'];
		
		if(item['c'][1]['v'] == 'sell'){
			table.addRow([ horas , valor, 0]);
			return;
		}
		
		table.addRow([ horas , 0, valor]);
		
	}
	
	var result = google.visualization.data.group(table, 
			[ 	
				{
					'column' : 0,
					'label' : 'Horas',
					'type' : 'number'
				}
			], 
			[ 
			{
				'column' : 1,
				'label' : 'sell',
				'aggregation' : google.visualization.data.sum,
				'type' : 'number'
			},
			{
				'column' : 2,
				'label' : 'buy',
				'aggregation' : google.visualization.data.sum,
				'type' : 'number'
			} 
			]
		);
		
	
	return result;
}
