resource "docker_image" "nginx" {

  name = "nginx:latest"

}

resource "docker_container" "webserver" {

  image = docker_image.nginx.image_id

  name = "cloudmart-nginx"

  ports {

    internal = 80

    external = 8085

  }

}