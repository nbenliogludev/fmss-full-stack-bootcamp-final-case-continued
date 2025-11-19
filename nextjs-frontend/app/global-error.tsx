"use client";

export default function GlobalError({
                                        error,
                                        reset,
                                    }: {
    error: Error & { digest?: string };
    reset: () => void;
}) {
    return (
        <html>
        <body className="min-h-screen flex flex-col items-center justify-center">
        <h2 className="text-2xl font-bold mb-4">Something went wrong</h2>
        <p className="mb-4 text-gray-600">
            {error.message || "Unexpected error occurred."}
        </p>
        <button
            onClick={() => reset()}
            className="px-4 py-2 rounded-md bg-blue-600 text-white"
        >
            Try again
        </button>
        </body>
        </html>
    );
}
