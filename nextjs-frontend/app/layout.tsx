import "styles/globals.css";
import type { ReactNode } from "react";
import Header from "@elements/Header";
import AppProviders from "./AppProviders";

export const dynamic = "force-dynamic";

interface IProps {
    children: ReactNode;
}

export default function RootLayout({ children }: IProps) {
    return (
        <html lang="en">
        <body>
        <AppProviders>
            <Header />
            <div className="min-h-screen">{children}</div>
        </AppProviders>
        </body>
        </html>
    );
}
