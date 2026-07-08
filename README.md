# FoodHub – Food Ordering Portal & DevOps Showcase

Welcome to **FoodHub**, a modern, Swiggy/Zomato-inspired food ordering portal designed as an industry-grade project. This project is configured to demonstrate an end-to-end DevOps workflow, combining a **Java 17 + Spring Boot 3.x** MVC backend, a responsive **Bootstrap 5 + Thymeleaf** frontend, and modern cloud deployment configurations.

---

## 🏗️ Project Architecture & Layout

The project structure matches standard enterprise DevOps repository structures:

```text
FoodHub-DevOps/
├── app/                  # Java Spring Boot MVC Web Application
│   ├── src/              # Source code (Controllers, Services, JPA Entities, static views)
│   └── pom.xml           # Maven Project Dependencies
├── terraform/            # Cloud Infrastructure as Code (Azure ACR, AKS)
├── kubernetes/           # K8s manifest files (Deployment, LoadBalancer Service)
├── pipeline/             # CI/CD Azure DevOps Pipeline YAML definition
├── Dockerfile            # Multi-stage Docker packaging configuration
└── README.md             # Documentation (This file)
```

---

## 🛠️ Tech Stack & Prerequisites

- **Backend**: Java 17, Spring Boot 3.3.x, Spring MVC, Spring Data JPA, Maven.
- **Frontend**: Thymeleaf, Bootstrap 5, Custom CSS3, Javascript (ES6).
- **Database**: H2 Database (In-Memory; no installation required).
- **DevOps**: Docker, Kubernetes, Azure Container Registry (ACR), Azure Kubernetes Service (AKS), Terraform, Azure DevOps Pipelines, GitHub.

To run or deploy this project, install:
- [Java Development Kit (JDK) 17](https://adoptium.net/temurin/releases/?version=17)
- [Apache Maven](https://maven.apache.org/)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [Terraform CLI](https://developer.hashicorp.com/terraform/downloads)
- [Azure CLI](https://learn.microsoft.com/en-us/cli/azure/install-azure-cli)
- [kubectl CLI](https://kubernetes.io/docs/tasks/tools/)

---

## 🚀 Step 1: Run the Application Locally (Maven)

The backend utilizes an in-memory H2 database which automatically spins up, builds schemas, and seeds **6 Restaurants** and **35 Food Items** using `schema.sql` and `data.sql` scripts on application startup.

1. Navigate to the app directory:
   ```bash
   cd app
   ```
2. Build and package the JAR file:
   ```bash
   mvn clean package
   ```
3. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```
4. Access the web interface in your browser:
   * **Web Portal**: [http://localhost:8080](http://localhost:8080)
   * **H2 DB Console**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
     * *JDBC URL*: `jdbc:h2:mem:foodhubdb`
     * *Username*: `sa`
     * *Password*: `password`

---

## 🐳 Step 2: Containerization (Docker)

A multi-stage Dockerfile is configured at the root. Stage 1 compiles the Java bytecode via Maven, and Stage 2 runs it on a lightweight, secure Eclipse Temurin JRE-Alpine base.

1. Build the Docker Image:
   ```bash
   # Make sure you are in the root directory (FoodHub-DevOps/)
   docker build -t foodhub-app:latest .
   ```
2. Run the Docker Container:
   ```bash
   docker run -d -p 8080:8080 --name foodhub-portal foodhub-app:latest
   ```
3. Access the portal at [http://localhost:8080](http://localhost:8080).

---

## ☁️ Step 3: Infrastructure Provisioning (Terraform)

Provision resource networks, registries, and container services in Microsoft Azure.

1. Authenticate with Azure:
   ```bash
   az login
   ```
2. Navigate to the terraform directory:
   ```bash
   cd terraform
   ```
3. Initialize Terraform plugins, preview configurations, and apply the execution plan:
   ```bash
   terraform init
   terraform plan
   terraform apply -auto-approve
   ```
4. Terraform outputs the registry details, cluster resources, and the login credentials connection command.

---

## ☸️ Step 4: Cluster Orchestration (Kubernetes)

Configure connectivity to your Azure Kubernetes Service cluster and launch the application replicas.

1. Retrieve AKS access credentials using the command outputted by Terraform:
   ```bash
   az aks get-credentials --resource-group foodhub-devops-rg --name foodhub-aks
   ```
2. (Optional) Substitute the container image placeholder in [kubernetes/deployment.yaml](kubernetes/deployment.yaml) with your newly created Azure Container Registry login server (e.g. `foodhubregistrydevops.azurecr.io/foodhub-app:latest`).
3. Deploy the manifests:
   ```bash
   kubectl apply -f kubernetes/deployment.yaml
   kubectl apply -f kubernetes/service.yaml
   ```
4. Monitor deployment rollouts and external load balancer IPs:
   ```bash
   kubectl get pods -l app=foodhub-app
   kubectl get service foodhub-service --watch
   ```
5. Grab the **EXTERNAL-IP** and visit `http://<EXTERNAL-IP>` in your browser to view the live portal.

---

## 🔄 Step 5: Continuous Integration (Azure DevOps Pipelines)

The pipeline script [pipeline/azure-pipelines.yml](pipeline/azure-pipelines.yml) defines a multi-stage delivery pipeline:
1. **Stage 1 (Maven Build & Test)**: Imports Java 17, compiles code, and runs tests.
2. **Stage 2 (ACR Container Publish)**: Authenticates to your Azure service connection, runs a Docker build context at the root workspace, and pushes the image tagged with the build ID and `latest` directly to your ACR registry.

To integrate:
1. Push this project to your GitHub or Azure Repos repository.
2. In Azure DevOps, go to **Pipelines** -> **New Pipeline** -> Select your repo.
3. Choose **Existing Azure Pipelines YAML file** and point to `/pipeline/azure-pipelines.yml`.
4. Create a Service Connection in Azure DevOps pointing to your Azure Resource Group and call it `AzureDevOps-ServiceConnection` (or modify the variable inside the YAML file).
