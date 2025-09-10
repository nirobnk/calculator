import { useState } from "react";
import { Calculator, RefreshCcw } from "lucide-react";

function App() {
  const [x, setX] = useState<number>(0);
  const [y, setY] = useState<number>(0);
  const [op, setOp] = useState("add");
  const [result, setResult] = useState<string | null>(null);

  const calculate = async () => {
    setResult("Calculating...");
    try {
      const res = await fetch("http://localhost:8080/api/calc", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ x, y, op }),
      });
      const data = await res.json();
      if (data.result !== undefined) {
        setResult(`Result: ${data.result}`);
      } else {
        setResult(`Error: ${data.error}`);
      }
    } catch {
      setResult("Network error");
    }
  };

  const reset = () => {
    setX(0);
    setY(0);
    setOp("add");
    setResult(null);
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-indigo-100 via-white to-indigo-50">
      <div className="bg-white shadow-xl rounded-2xl p-6 w-96 border">
        <div className="flex items-center gap-2 mb-6">
          <Calculator className="text-indigo-600 w-7 h-7" />
          <h1 className="text-2xl font-bold text-gray-800">Calculator</h1>
        </div>

        <div className="flex flex-col gap-4">
          <input
            type="number"
            value={x}
            onChange={(e) => setX(Number(e.target.value))}
            className="p-2 border rounded-lg focus:ring-2 focus:ring-indigo-400"
            placeholder="Enter first number"
          />

          <select
            value={op}
            onChange={(e) => setOp(e.target.value)}
            className="p-2 border rounded-lg focus:ring-2 focus:ring-indigo-400"
          >
            <option value="add">➕ Add</option>
            <option value="sub">➖ Subtract</option>
            <option value="mul">✖ Multiply</option>
            <option value="div">➗ Divide</option>
          </select>

          <input
            type="number"
            value={y}
            onChange={(e) => setY(Number(e.target.value))}
            className="p-2 border rounded-lg focus:ring-2 focus:ring-indigo-400"
            placeholder="Enter second number"
          />

          <button
            onClick={calculate}
            className="w-full flex items-center justify-center gap-2 bg-indigo-600 hover:bg-indigo-700 text-white font-semibold py-2 rounded-lg transition"
          >
            <Calculator className="w-5 h-5" />
            Calculate
          </button>

          <button
            onClick={reset}
            className="w-full flex items-center justify-center gap-2 border border-gray-300 hover:bg-gray-100 text-gray-700 py-2 rounded-lg transition"
          >
            <RefreshCcw className="w-5 h-5" />
            Reset
          </button>

          {result && (
            <p className="text-center mt-4 text-lg font-medium text-gray-800">
              {result}
            </p>
          )}
        </div>
      </div>
    </div>
  );
}

export default App;