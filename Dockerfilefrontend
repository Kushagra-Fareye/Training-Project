FROM nginx
COPY build /usr/share/nginx/to-do/build

COPY default.conf /etc/nginx/conf.d

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]