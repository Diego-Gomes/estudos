using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using MongoDB.Driver;
using MongoDB.Bson;

namespace Alura.GoogleMaps.Web.Models
{
    public class ConectandoMongoDBGeo
    {
        private static IMongoClient _client;
        private static IMongoDatabase _database;
        private readonly string nome_collection = "ariports";
        public ConectandoMongoDBGeo()
        {
            if (_database == null)
            {
                String url = "mongodb://diego:root@192.168.100.175:27017/admin";
                MongoUrl mongoUrl = new MongoUrl(url);
                _client = new MongoClient(mongoUrl);
                _database = _client.GetDatabase(mongoUrl.DatabaseName);
            }
        }

        public IMongoCollection<Aeroporto> Aiports
        {
            get { return _database.GetCollection<Aeroporto>(nome_collection); }
        }
    }
}