time (
  printf  "\n\n\n"
  printf  "processando..."
  printf  "\n\n\n"
  sudo docker stop $(sudo docker ps -aq)
  sudo docker rm $(sudo docker ps -aq)
  sudo docker rmi $(sudo docker images -q)
  sudo docker system prune -f
  cd /home/maringa-noticias-api
  sudo docker-compose up --build -d
)

