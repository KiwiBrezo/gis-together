FROM node:18-alpine

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app

COPY . .

RUN npm install
RUN rm .env
RUN mv .env.prod .env

EXPOSE 3000

CMD ["npm", "run", "start"]
