using Leftware.Examples.SignalR.Models;
using Microsoft.AspNetCore.SignalR;
using System.Text.Json;

namespace Leftware.Examples.SignalR.Commands;

public interface IGameCommand
{
    public string CommandName { get; }
    //public Task Execute(Hub hub, IGameServerContext context, string argument);
    public IAsyncEnumerable<(string, object)> Execute(Hub hub, IGameServerContext context, string argument);
}
