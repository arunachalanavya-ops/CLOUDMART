# CloudMart -- Setup & Deployment Commands

## 1. Navigate to the project

``` bash
cd CLOUDMART
```

------------------------------------------------------------------------

## 2. Build the Backend Docker Image

``` bash
cd backend
docker build -t cloudmart-backend .
```

Verify the image:

``` bash
docker images
```

------------------------------------------------------------------------

## 3. Run the Backend Locally

``` bash
docker run -p 8080:8080 cloudmart-backend:latest
```

Test the application:

``` bash
curl http://localhost:8080/health
curl http://localhost:8080/products
```

------------------------------------------------------------------------

## 4. Deploy to Kubernetes

Return to the project root:

``` bash
cd ..
```

Create the deployment:

``` bash
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
```

Verify resources:

``` bash
kubectl get deployments
kubectl get pods
kubectl get services
```

Forward the service to your local machine:

``` bash
kubectl port-forward service/cloudmart-backend-service 8080:80
```

If required, inspect resources:

``` bash
kubectl describe pod <pod-name>
kubectl describe deployment cloudmart-backend
kubectl logs <pod-name>
```

Restart the deployment:

``` bash
kubectl rollout restart deployment cloudmart-backend
```

------------------------------------------------------------------------

## 5. Docker Commands

Running containers:

``` bash
docker ps
```

Stop a container:

``` bash
docker stop <container-id>
```

Remove a container:

``` bash
docker rm <container-id>
```

Inspect an image:

``` bash
docker image inspect cloudmart-backend:latest
```

Check Docker version:

``` bash
docker version
```

------------------------------------------------------------------------

## 6. Ansible

Install Ansible (Ubuntu/WSL):

``` bash
sudo apt update
sudo apt install ansible -y
```

Navigate to the Ansible directory:

``` bash
cd ansible
```

Dry run:

``` bash
ansible-playbook -i inventory.ini playbook.yaml --check
```

Run the playbook:

``` bash
ansible-playbook -i inventory.ini playbook.yaml
```

Run with sudo prompt:

``` bash
ansible-playbook -i inventory.ini playbook.yaml --ask-become-pass
```

Verify sudo:

``` bash
sudo -v
```

------------------------------------------------------------------------

## 7. Useful Kubernetes Commands

``` bash
kubectl get pods
kubectl get services
kubectl get deployments
kubectl config current-context
kubectl delete deployment cloudmart-backend
```

------------------------------------------------------------------------

## Troubleshooting

### Docker build fails with `pom.xml not found`

Ensure you are inside the `backend` directory before running:

``` bash
docker build -t cloudmart-backend .
```

### `ErrImageNeverPull`

Confirm the image exists:

``` bash
docker images
```

Verify the deployment uses:

``` yaml
image: cloudmart-backend:latest
imagePullPolicy: Never
```

### HTTPS health check fails

Use HTTP instead:

``` bash
curl http://localhost:8080/health
```

### Port forwarding fails

Check whether the pods are running:

``` bash
kubectl get pods
```

If the pods are not `Running`, inspect them:

``` bash
kubectl describe pod <pod-name>
kubectl logs <pod-name>
```
