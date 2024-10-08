import { useState } from 'react';
import axios from 'axios';
import loadingSvg from '/spinner.svg';

import './App.css';

const App = () => {
  const [url, setUrl] = useState('');
  const [responseData, setResponseData] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = async (e: any) => {
    e.preventDefault();
    setIsLoading(true);
    // await new Promise(f => setTimeout(f, 1000));

    try {
      const response = await axios.get(`/urltest?url=${url}`);
      setResponseData(response.data);
    } catch (error) {
      console.error(error);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="container">
      <h1>URL Tester</h1>
      <p>
        This simple app sends a url to the server, which in turn will test
        the connectivity of such resource.
      </p>
      <form onSubmit={handleSubmit}>
        <input type="text" value={url} onChange={(e) => setUrl(e.target.value)} />
        <button type="submit">Submit</button>
      </form>
      {isLoading && <img src={loadingSvg} className="loading" alt="loading" />}
      {responseData && (
        <pre>{responseData}</pre>
      )}
    </div>
  );
};

export default App;