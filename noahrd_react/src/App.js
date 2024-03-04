import { useState } from "react";
const App = () => {
  const [counter, setCounter] = useState(0);
  const [value, setValue] = useState(0);

  const handlerCounter = () => {
    setCounter(counter + 1);
    setValue(value + counter);
  };

  return (
    <div className="container">
      <h1>Hello World from React</h1>
      <div>
        <button onClick={handlerCounter}>Click me</button>
      </div>
      <span>{value}</span>
    </div>
  );
};
export default App;
