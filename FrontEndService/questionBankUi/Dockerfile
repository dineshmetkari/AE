FROM node:9

# Create app directory
WORKDIR /usr/src/app

# Install app dependencies
COPY package.json /usr/src/app
# For npm@5 or later, copy package-lock.json as well
# COPY package.json package-lock.json ./

RUN npm install
# If you are building your code for production
# RUN npm install

# Bundle app source
COPY . /usr/src/app

EXPOSE 5001

#RUN npm build --prod


CMD ["npm", "start"]
