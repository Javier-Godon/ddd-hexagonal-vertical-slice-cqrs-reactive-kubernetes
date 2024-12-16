schema AppConfig:
    replicas: int
    type: str

app: AppConfig {
    replicas: 2
    type: 'Deployment'
}