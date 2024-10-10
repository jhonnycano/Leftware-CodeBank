import React from 'react';
import Game from './Models/Game';

interface GameListProps {
  games: Game[];
}

const GameList: React.FC<GameListProps> = ({ games }) => {
  return (
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Type</th>
          <th>User</th>
        </tr>
      </thead>
      <tbody>
        {games.map((game) => (
          <tr key={game.id}>
            <td>{game.id}</td>
            <td>{game.type}</td>
            <td>{game.user}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default GameList;