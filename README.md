# 🧮 Java Calculator App - CI/CD Pipeline with Kubernetes & Monitoring

This project demonstrates a complete DevOps pipeline for a **Java-based Calculator App** using modern tools such as:

- Git & GitHub (Version Control)
- Jenkins (CI/CD Pipeline)
- Docker (Containerization)
- Amazon ECR (Docker Image Registry)
- Kubernetes (Deployment on AWS EKS)
- Prometheus & Grafana (Monitoring)
- Helm (Package Manager for K8s)

---

## 📦 Tech Stack

| Layer       | Tool/Service             |
|-------------|--------------------------|
| SCM         | Git, GitHub              |
| CI/CD       | Jenkins                  |
| Build Tool  | `javac` (Java Compiler)  |
| Container   | Docker                   |
| Registry    | AWS ECR                  |
| Orchestration | Kubernetes (EKS)       |
| Monitoring  | Prometheus + Grafana     |

---

## 📁 Project Structure

```bash
java-calculator-app/
│
├── App.java                   # Java source code (Calculator API)
├── Dockerfile                 # Builds Java app Docker image
├── Jenkinsfile                # Jenkins pipeline config
├── k8s/
│   ├── deployment.yaml        # Kubernetes deployment config
│   ├── service.yaml           # Kubernetes service config
│   └── namespace.yaml         # Custom K8s namespace
├── monitoring/
│   └── prometheus-grafana/    # Helm charts for monitoring
├── docs/                      # Screenshots and documentation
└── README.md                  # Project overview
