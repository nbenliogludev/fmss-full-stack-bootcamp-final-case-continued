/** @type {import('next').NextConfig} */
const nextConfig = {
    reactStrictMode: true,
    swcMinify: true,

    // ✅ Ignore TypeScript errors during production build
    typescript: {
        ignoreBuildErrors: true,
    },

    // ✅ Ignore ESLint errors during production build
    eslint: {
        ignoreDuringBuilds: true,
    },
};

module.exports = nextConfig;
