target "docker-metadata-action" {}

target "default" {
  inherits = ["docker-metadata-action"]
  context = "."
  dockerfile = "Dockerfile"
  attest = [
    {
      type = "provenance"
      mode = "max"
    },
    {
      type = "sbom"
    }
  ]
}
