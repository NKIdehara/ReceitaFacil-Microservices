FROM node:alpine AS receitafacil
# ENV NODE_ENV development
WORKDIR /receitafacil
COPY ./package*.json /receitafacil
RUN npm install
COPY . .
CMD ["npm","start"]