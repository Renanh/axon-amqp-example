FROM rabbitmq:3-management

COPY custom_definitions.json /etc/rabbitmq/
COPY rabbitmq.conf /etc/rabbitmq/

CMD ["rabbitmq-server"]