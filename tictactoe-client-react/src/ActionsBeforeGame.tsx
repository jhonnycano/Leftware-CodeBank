import React, { useState, useEffect } from 'react';

interface ActionsBeforeGameProps {
  webSocket: WebSocket;
  onGameListUpdated: (games: Game[]) => void;
  onGameCreated: (game: Game) => void;
}

interface Game {
  id: string;
  type: string;
  user: string;
}

const ActionsBeforeGame: React.FC<ActionsBeforeGameProps> = ({ webSocket, onGameListUpdated, onGameCreated }) => {
  const [isLoading, setIsLoading] = useState(false);

  const handleGetGames = () => {
    setIsLoading(true);
    webSocket.send('GetGameList');
  };

  const handleCreateGame = () => {
    setIsLoading(true);
    webSocket.send('CreateGame');
  };

  useEffect(() => {
    const handleMessage = (event: MessageEvent) => {
      const message = JSON.parse(event.data);
      if (message.type === 'GameListUpdated') {
        onGameListUpdated(message.data);
        setIsLoading(false);
      } else if (message.type === 'GameCreated') {
        onGameCreated(message.data);
        setIsLoading(false);
      }
    };

    webSocket.addEventListener('message', handleMessage);

    return () => {
      webSocket.removeEventListener('message', handleMessage);
    };
  }, [webSocket, onGameListUpdated, onGameCreated]);

  return (
    <div>
      <button onClick={handleGetGames} disabled={isLoading}>Get Games</button>
      <button onClick={handleCreateGame} disabled={isLoading}>Create Game</button>
    </div>
  );
};

export default ActionsBeforeGame;