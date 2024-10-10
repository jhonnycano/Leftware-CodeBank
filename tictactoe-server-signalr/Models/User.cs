namespace Leftware.Examples.SignalR.Models;

public sealed class User
{
    public string Id { get; }

    public string Name { get; }

    public DateTime LatestActivity { get; set; }

    public User(string id, string name, DateTime latestActivity)
    {
        Id = id;
        Name = name;
        LatestActivity = latestActivity;
    }
}
