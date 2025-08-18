import { useState } from "react";
import MessageList from "./MessageList";
import UserInput from "./UserInput";

let idCounter = 1;

export default function ChatWindow() {
  const [messages, setMessages] = useState([
    {
      id: idCounter++,
      role: "ai",
      text: "Hi! I’m your SwiftCart assistant. Ask about orders or products.",
      timestamp: Date.now(),
    },
  ]);
  const [input, setInput] = useState("");
  const [loading, setLoading] = useState(false);

  const sendMessage = () => {
    const trimmed = input.trim();
    if (!trimmed) return;

    const userMsg = {
      id: idCounter++,
      role: "user",
      text: trimmed,
      timestamp: Date.now(),
    };
    setMessages((prev) => [...prev, userMsg]);
    setInput("");
    setLoading(true);

    // Milestone 6: fake AI reply; Milestone 7 will call backend.
    setTimeout(() => {
      const aiMsg = {
        id: idCounter++,
        role: "ai",
        text: `You said: ${trimmed}`,
        timestamp: Date.now(),
      };
      setMessages((prev) => [...prev, aiMsg]);
      setLoading(false);
    }, 400);
  };

  return (
    <div className="chat-window">
      <header className="chat-header">SwiftCart Support</header>
      <MessageList messages={messages} />
      {loading && <div className="loading">Thinking…</div>}
      <UserInput value={input} onChange={setInput} onSend={sendMessage} disabled={loading} />
    </div>
  );
}
