import GameList from './GameList';
import Game from './Models/Game';

function Games(games: Game[]) {
  return (
    <GameList games={games} />
  );
}

export default Games;