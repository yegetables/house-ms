import os

os.system("mvn package -Dskip.test=true && docker-compose up --build")
