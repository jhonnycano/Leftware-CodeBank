using Leftware.Examples.SignalR.Models;

namespace Leftware.Examples.SignalR;

public interface IGameServerContext
{
    public IDictionary<string, User> Users { get; }
    public IDictionary<string, Game> Games { get; }
}

public sealed class GameServerContext : IGameServerContext
{
    private readonly IDictionary<string, User> _users;
    private readonly IDictionary<string, Game> _games;

    public IDictionary<string, User> Users => _users;

    public IDictionary<string, Game> Games => _games;

    public GameServerContext()
    {
        _users = new Dictionary<string, User>();
        _games = new Dictionary<string, Game>();
    }
}
