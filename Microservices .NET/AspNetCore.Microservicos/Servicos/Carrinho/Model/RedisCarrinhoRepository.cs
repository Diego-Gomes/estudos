﻿using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using StackExchange.Redis;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Carrinho.API.Model
{
    public class RedisCarrinhoRepository : ICarrinhoRepository
    {
        private const int CARRINHO_DB_INDEX = 0;
        private readonly ILogger<RedisCarrinhoRepository> _logger;
        private readonly IConnectionMultiplexer _redis;
        private readonly IDatabase _database;

        public RedisCarrinhoRepository(ILogger<RedisCarrinhoRepository> logger, IConnectionMultiplexer redis)
        {
            _logger = logger;
            _redis = redis;
            _database = redis.GetDatabase(CARRINHO_DB_INDEX);
        }

        public async Task<bool> DeleteCarrinhoAsync(string id)
        {
            return await _database.KeyDeleteAsync(id);
        }

        public async Task<CarrinhoCliente> GetCarrinhoAsync(string clienteId)
        {
            if (string.IsNullOrWhiteSpace(clienteId))
                throw new ArgumentException();

            var data = await _database.StringGetAsync(clienteId);
            if (data.IsNullOrEmpty)
            {
                return await UpdateCarrinhoAsync(new CarrinhoCliente(clienteId));
            }
            return JsonConvert.DeserializeObject<CarrinhoCliente>(data);
        }

        public IEnumerable<string> GetUsuarios()
        {
            var server = GetServer();
            return server.Keys()?.Select(k => k.ToString());
        }

        public async Task<CarrinhoCliente> UpdateCarrinhoAsync(CarrinhoCliente carrinho)
        {
            var criado = await _database.StringSetAsync(carrinho.ClienteId, JsonConvert.SerializeObject(carrinho));
            if (!criado)
            {
                _logger.LogError("Erro ao atualizar o carrinho.");
                return null;
            }
            return await GetCarrinhoAsync(carrinho.ClienteId);
        }

        public async Task<CarrinhoCliente> AddCarrinhoAsync(string clienteId, ItemCarrinho item)
        {
            if (item == null)
                throw new ArgumentNullException();

            if (string.IsNullOrWhiteSpace(item.ProdutoId))
                throw new ArgumentException();

            if (item.Quantidade <= 0)
                throw new ArgumentOutOfRangeException();

            var carrinho = await GetCarrinhoAsync(clienteId);
            ItemCarrinho itemDB = carrinho.Itens.Where(i => i.ProdutoId == item.ProdutoId).SingleOrDefault();
            if (itemDB == null)
            {
                itemDB = new ItemCarrinho(item.Id, item.ProdutoId, item.ProdutoNome, item.PrecoUnitario, item.Quantidade);
                carrinho.Itens.Add(item);
            }
            return await UpdateCarrinhoAsync(carrinho);
        }

        public async Task<UpdateQuantidadeOutput> UpdateCarrinhoAsync(string clienteId, UpdateQuantidadeInput item)
        {
            if (item == null)
                throw new ArgumentNullException();

            if (string.IsNullOrWhiteSpace(item.ProdutoId))
                throw new ArgumentException();

            if (item.Quantidade < 0)
                throw new ArgumentOutOfRangeException();

            var carrinho = await GetCarrinhoAsync(clienteId);
            ItemCarrinho itemDB = carrinho.Itens.Where(i => i.ProdutoId == item.ProdutoId).SingleOrDefault();
            itemDB.Quantidade = item.Quantidade;
            if (item.Quantidade == 0)
            {
                carrinho.Itens.Remove(itemDB);
            }
            CarrinhoCliente carrinhoCliente = await UpdateCarrinhoAsync(carrinho);
            return new UpdateQuantidadeOutput(itemDB, carrinhoCliente);
        }

        private IServer GetServer()
        {
            var endpoints = _redis.GetEndPoints();
            return _redis.GetServer(endpoints.First());
        }

    }
}
