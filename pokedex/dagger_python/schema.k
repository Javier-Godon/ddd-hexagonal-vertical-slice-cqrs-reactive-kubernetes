schema AppConfig:
    replicas: int
    type: str
    other: str = 'other'

app: AppConfig {
    replicas: 2
    type: 'Deployment'
}