import { Link} from "react-router-dom";

export default function NotFoundPage() {
    return <div>
        Not found
        <Link to="/">Home</Link>
    </div>;
}