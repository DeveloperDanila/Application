import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';

const App = () => {

  const [groups, setGroups] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);

    fetch('/api/v1/get/hello/multiple')
      .then(response => response.json())
      .then(data => {
        setGroups(data);
        setLoading(false);
      })
  }, []);

  if (loading) {
    return <p>Loading...</p>;
  }

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <div className="App-intro">
          <h2>Hello's List</h2>
          {groups.map(group =>
            <div key={group.id}>
              {group.value}
            </div>
            )}
        </div>
      </header>
    </div>
    );
}

export default App;
