using Leftware.Examples.SignalR.Commands;
using Microsoft.Extensions.FileProviders;

namespace Leftware.Examples.SignalR;

public static class Program
{
    public static void Main(string[] args)
    {
        var builder = WebApplication.CreateBuilder(args);
        builder.Services.AddSignalR();

        builder.Services.AddSingleton<IGameServerContext, GameServerContext>();
        builder.Services.AddKeyedSingleton<IGameCommand, AddUserGameCommand>(CommandNames.ADD_USER);
        builder.Services.AddKeyedSingleton<IGameCommand, GetGameListCommand>(CommandNames.GET_GAME_LIST);
        builder.Services.AddKeyedSingleton<IGameCommand, CreateGameCommand>(CommandNames.CREATE_GAME);
        builder.Services.AddKeyedSingleton<IGameCommand, JoinGameCommand>(CommandNames.JOIN_GAME);
        builder.Services.AddKeyedSingleton<IGameCommand, ProcessMoveCommand>(CommandNames.PROCESS_MOVE);

        var app = builder.Build();

        app.UseStaticFiles();
        app.UseStaticFiles(new StaticFileOptions
        {
            FileProvider = new PhysicalFileProvider(Path.Combine(app.Environment.ContentRootPath, "node_modules")),
            RequestPath = "/node_modules",
        });
        app.MapHub<GameHub>("/tictactoe");
        app.Run();
    }
}
