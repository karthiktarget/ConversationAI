export default function UserInput({ value, onChange, onSend, disabled }) {
  const handleSubmit = (e) => {
    e.preventDefault();
    if (value.trim()) onSend();
  };

  return (
    <form className="user-input" onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Type a message…"
        value={value}
        onChange={(e) => onChange(e.target.value)}
        disabled={disabled}
        autoFocus
      />
      <button type="submit" disabled={disabled || !value.trim()}>
        Send
      </button>
    </form>
  );
}
