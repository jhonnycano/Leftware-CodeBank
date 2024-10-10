using Leftware.Examples.SignalR.Utils;
using System.Text.Json;

namespace Leftware.Examples.SignalR.Models;

public sealed class Game
{
    public string Id { get; }
    public GameType GameType { get; }
    public GameStatus GameStatus { get; set; }
    public User UserOwner { get; }
    public IDictionary<string, User> Users { get; }
    public string? Turn { get; private set; }
    public IDictionary<string, string> UserGameInfo { get; private set; }
    public string[] GameState { get; private set; }
    public User? UserWinner { get; private set; }

    public Game(GameType gameType, User userOwner)
    {
        Id = Guid.NewGuid().ToString();
        GameType = gameType;
        GameStatus = GameStatus.New;
        UserOwner = userOwner;
        Users = new Dictionary<string, User> { { userOwner.Id, userOwner } };
        UserGameInfo = new Dictionary<string, string> { };
        GameState = new string[9];
    }

    public void Initialize()
    {
        GameStatus = GameStatus.InProgress;
        GameState = Enumerable.Range(0, 9).Select(_ => "_").ToArray();
        string userWithFirstTurn = new Random().GetItems<string>(Users.Keys.ToArray().AsSpan(), 1).First();
        UserGameInfo = new Dictionary<string, string> {
            { userWithFirstTurn, "O" },
            { Users.Keys.First(u => u != userWithFirstTurn), "X" },
        };
        Turn = userWithFirstTurn;
    }

    public JsonDocument ProcessMove(string user, JsonDocument moveDetail)
    {
        string userAvatar = UserGameInfo[user];
        int move = moveDetail.RootElement.GetProperty("move").GetInt32();
        GameState[move] = userAvatar;

        string? winner = GetWinner();
        string gameResult;
        if (winner is not null)
        {
            string winnerUserId = UserGameInfo.First(v => v.Value == winner).Key;
            GameStatus = GameStatus.Finished;
            UserWinner = Users[winnerUserId];
            gameResult = "Finished";
        }
        else
        {
            gameResult = "In progress";
            bool isFinished = IsFinished();
            if (isFinished)
            {
                GameStatus = GameStatus.Finished;
                gameResult = "Finished";
                Turn = null;
            }
            Turn = Users.Values.First(u => u.Id != user).Id;
        }

        TicTacToeSimpleMoveInfo result = new(gameResult, Turn, UserWinner?.Id, UserWinner?.Name, GameState);
        string json = JsonHelper.Serialize(result);
        return JsonDocument.Parse(json);
    }

    private string? GetWinner()
    {
        List<List<int>> winnerLines = new List<List<int>> {
            new List<int> { 0, 1, 2, },
            new List<int> { 3, 4, 5, },
            new List<int> { 6, 7, 8, },
            new List<int> { 0, 3, 6, },
            new List<int> { 1, 4, 7, },
            new List<int> { 2, 5, 8, },
            new List<int> { 0, 4, 8, },
            new List<int> { 2, 4, 6, },
        };

        foreach (List<int> winnerLine in winnerLines)
        {
            int ind1 = winnerLine[0];
            int ind2 = winnerLine[1];
            int ind3 = winnerLine[2];

            string val1 = GameState[ind1];
            string val2 = GameState[ind2];
            string val3 = GameState[ind3];

            if (val1 == "_" || val1 != val2) continue;
            if (val2 == "_" || val2 != val3) continue;
            if (val3 == "_" || val1 != val3) continue;
            return val1;
        }

        return null;
    }

    private bool IsFinished()
    {
        for (int i = 0; i < 9; i++)
        {
            if (GameState[i] == "_") return false;
        }

        return true;
    }
}
