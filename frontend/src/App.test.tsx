import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import { vi, describe, test, expect } from "vitest";
import App from "./App";

window.fetch = vi.fn(() =>
  Promise.resolve({
    json: () => Promise.resolve({ result: 5 }),
  })
) as any;

describe("Calculator App", () => {
  test("renders calculator title", () => {
    render(<App />);
    expect(screen.getByText(/Calculator/i)).toBeInTheDocument();
  });

  test("performs calculation", async () => {
    render(<App />);
    const inputX = screen.getAllByRole("spinbutton")[0];
    const inputY = screen.getAllByRole("spinbutton")[1];
    const button = screen.getByText(/Calculate/i);

    fireEvent.change(inputX, { target: { value: "2" } });
    fireEvent.change(inputY, { target: { value: "3" } });
    fireEvent.click(button);

    await waitFor(() => {
      expect(screen.getByText(/Result: 5/i)).toBeInTheDocument();
    });
  });
});
