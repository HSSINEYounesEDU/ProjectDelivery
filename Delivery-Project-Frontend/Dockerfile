# Stage 1: Build with Node.js
FROM node:18.13-alpine as builder
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

# Stage 2: Setup Nginx server
FROM nginx:alpine
COPY --from=builder /app/dist/blogs-front /usr/share/nginx/html
