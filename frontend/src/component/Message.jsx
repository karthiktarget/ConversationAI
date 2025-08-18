import React from "react";
export default function Message({ message }) {
  const isUser = message.role === "user";
  return (
    <div className={`message ${isUser ? "user" : "ai"}`}>
      <div className="bubble">{message.text}</div>
      <span className="timestamp">
        {new Date(message.timestamp).toLocaleTimeString([], {
          hour: "2-digit",
          minute: "2-digit",
        })}
      </span>
    </div>
  );
}
