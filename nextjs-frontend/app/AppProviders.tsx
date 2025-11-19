// app/AppProviders.tsx
"use client";

import type { ReactNode } from "react";
import Provider from "./Provider";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { useState } from "react";

interface Props {
    children: ReactNode;
}

export default function AppProviders({ children }: Props) {
    const [queryClient] = useState(() => new QueryClient());

    return (
        <Provider>
            <QueryClientProvider client={queryClient}>
                {children}
            </QueryClientProvider>
        </Provider>
    );
}
