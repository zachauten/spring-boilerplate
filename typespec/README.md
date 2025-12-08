# Typespec

1. Install shell dependencies: `nix-shell .`
2. Install typespec dependencies: `deno install`
3. Compile typespec: `tsp compile --output-dir out .`
4. Generate Java: `openapi-generator-cli generate -g java -i out/schema/openapi.yaml -o java`