FROM swaggerapi/swagger-ui:latest
MAINTAINER MARINA

ADD /audit-service/spec/audit-service.yml /usr/share/nginx/html/doc/audit-service.yaml
ADD /product-service/spec/product-service.yml /usr/share/nginx/html/doc/product-service.yaml
ADD /user-service/spec/user-service.yml /usr/share/nginx/html/doc/user-service.yaml

ENV URLS="[{ url: 'doc/audit-service.yaml', name: 'Audit'},{ url: 'doc/product-service.yaml', name: 'Product'},{ url: 'doc/user-service.yaml', name: 'User'},]"
ENV URLS_PRIMARY_NAME="User"