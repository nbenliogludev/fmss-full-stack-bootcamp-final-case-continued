# 1) Dependencies
FROM node:20 AS deps
WORKDIR /app

COPY package.json package-lock.json ./
RUN npm ci

# 2) Build
FROM node:20 AS builder
WORKDIR /app
ENV NODE_ENV=production

COPY --from=deps /app/node_modules ./node_modules
COPY . .
RUN npm run build

# 3) Runner
FROM node:20 AS runner
WORKDIR /app
ENV NODE_ENV=production
ENV PORT=3000

# Kullanıcı oluştur
RUN groupadd -g 1001 nodejs && useradd -u 1001 -g nodejs -s /bin/bash -m nextjs

# Bağımlılıkları ve build çıktısını kopyala
COPY --from=deps /app/node_modules ./node_modules
COPY --from=builder /app/package.json ./package.json
COPY --from=builder /app/public ./public
COPY --from=builder /app/.next ./.next
COPY --from=builder /app/next.config.* ./
COPY --from=builder /app/tsconfig.json ./tsconfig.json

USER nextjs

EXPOSE 3000
CMD ["npm", "start"]
