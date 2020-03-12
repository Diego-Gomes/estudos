using Alura.GoogleMaps.Web.Models;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Threading.Tasks;
using System.Net.Http;
using System.Net;
using Newtonsoft.Json;
using Alura.GoogleMaps.Web.Geocoding;
using MongoDB.Bson;
using MongoDB.Driver;
using MongoDB.Driver.GeoJsonObjectModel;


namespace Alura.GoogleMaps.Web.Controllers
{
    public class HomeController : Controller
    {
        // GET: Home
        public ActionResult Index()
        {
            //coordenadas quaisquer para mostrar o mapa
            var coordenadas = new Coordenada("São Paulo", "-23.64340873969638", "-46.730219057147224");
            return View(coordenadas);
        }

        public async Task<JsonResult> Localizar(HomeViewModel model)
        {
            Debug.WriteLine(model);

            //Captura a posição atual e adiciona a lista de pontos
            Coordenada coordLocal = ObterCoordenadasDaLocalizacao(model.Endereco);
            var aeroportosProximos = new List<Coordenada>();
            aeroportosProximos.Add(coordLocal);

            //Captura a latitude e longitude locais
            double lat = Convert.ToDouble(coordLocal.Latitude.Replace(".", ","));
            double lon = Convert.ToDouble(coordLocal.Longitude.Replace(".", ","));

            //Testa o tipo de aeroporto que será usado na consulta
            string tipoAero = string.Empty;

            if (model.Tipo == TipoAeroporto.Internacionais)
            {
                tipoAero = "International";
            }
            if (model.Tipo == TipoAeroporto.Municipais)
            {
                tipoAero = "Municipal";
            }

            //Captura o valor da distancia
            int distancia = model.Distancia * 1000;

            //Conecta MongoDB   
            ConectandoMongoDBGeo mongo = new ConectandoMongoDBGeo();

            //Configura o ponto atual no mapa           
            var geoJson2DGeographicCoordinates = new GeoJson2DGeographicCoordinates(lon, lat);
            var localizacao = new GeoJsonPoint<GeoJson2DGeographicCoordinates>(geoJson2DGeographicCoordinates);
            // filtro

            var filter = Builders<Aeroporto>.Filter.NearSphere(x => x.loc, localizacao, distancia);

            if (!string.IsNullOrEmpty(tipoAero) )
                filter = Builders<Aeroporto>.Filter.NearSphere(x => x.loc, localizacao, distancia) & Builders<Aeroporto>.Filter.Eq(x=> x.type, tipoAero);

            //Captura  a lista
            //Escreve os pontos
            try
            {

                IList<Aeroporto> Nova = mongo.Aiports.Find(filter).ToList();

                mongo.Aiports.Find(filter).ToList().ForEach(x => {

                    aeroportosProximos.Add(
                        new Coordenada(x.name,
                                        Convert.ToString(x.loc.Coordinates.Latitude).Replace(",", "."),
                                        Convert.ToString(x.loc.Coordinates.Longitude).Replace(",", ".")));




                }); 

            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);

                throw;
            }
            
            return Json(aeroportosProximos);
        }

        private Coordenada ObterCoordenadasDaLocalizacao(string endereco)
        {

            string key = "AIzaSyB_r0ln3-RMovbCsV6UIQvfusOqaCox3xc";

            string url = $"https://maps.googleapis.com/maps/api/geocode/json?address={endereco}&key={key}";
            Debug.WriteLine(url);

            var coord = new Coordenada("Não Localizado", "-10", "-10");
            var response = new WebClient().DownloadString(url);
            var googleGeocode = JsonConvert.DeserializeObject<GoogleGeocodeResponse>(response);
            //Debug.WriteLine(googleGeocode);

            if (googleGeocode.status == "OK")
            {
                coord.Nome = googleGeocode.results[0].formatted_address;
                coord.Latitude = googleGeocode.results[0].geometry.location.lat;
                coord.Longitude = googleGeocode.results[0].geometry.location.lng;
            }

            return coord;
        }
    }
}