import React, { useState } from 'react';

interface SetUserProps {
  onSessionStart: (username: string) => void;
}

const SetUser: React.FC<SetUserProps> = ({ onSessionStart }) => {
  const [username, setUsername] = useState('');
  const [isValid, setIsValid] = useState(false);

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const newUsername = event.target.value;
    setUsername(newUsername);  

    setIsValid(newUsername.length >= 3);
  };

  const handleSessionStart = () => {
    if (isValid) {
      onSessionStart(username);
    }
  };

  return (
    <div>
      <input
        type="text"
        placeholder="Enter your username"
        value={username}
        onChange={handleInputChange}
      />
      <button disabled={!isValid} onClick={handleSessionStart}>
        Start Session
      </button>
    </div>
  );
};

export default SetUser;