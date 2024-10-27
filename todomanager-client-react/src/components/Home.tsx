import { Alert } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { HomeInput } from '../interfaces/HomeInput';

const Home: React.FC<HomeInput> = ({ message, messageType }) => {
    return (
        <>
            {message && (
                <Alert variant={messageType || 'danger'}>
                    {message}
                </Alert>
            )}

            <h1>Todo Manager</h1>
            <p>This project implements a simple Todo Manager</p>

            <ul className=''>
                <li>
                    <Link to="/projects">Projects</Link>
                </li>
                <li>
                    <Link to="/projects/new">New Project</Link>
                </li>
            </ul>
        </>
    )
}

export default Home;
