FROM node:16 as build-stage

RUN npm install -g @angular/cli

WORKDIR /app

COPY ../package*.json ./

RUN npm install

COPY . .

RUN ng build

FROM nginx:alpine

COPY /dist/teste-elotech-frontend /usr/share/nginx/html
