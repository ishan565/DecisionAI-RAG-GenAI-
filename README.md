# DecisionAI: The Decision Supernova

<p align="center">
	<b>RAG-powered intelligence for real-world choices.</b><br/>
	Spring Boot precision in the backend. React velocity in the frontend.
</p>

<p align="center">
	<img alt="Java" src="https://img.shields.io/badge/Java-17-0B3D91?style=for-the-badge&logo=openjdk&logoColor=white" />
	<img alt="Spring Boot" src="https://img.shields.io/badge/Spring_Boot-3.x-2E7D32?style=for-the-badge&logo=springboot&logoColor=white" />
	<img alt="React" src="https://img.shields.io/badge/React-18-0D1117?style=for-the-badge&logo=react&logoColor=61DAFB" />
	<img alt="Vite" src="https://img.shields.io/badge/Vite-Frontend_Astro_Engine-8A2BE2?style=for-the-badge&logo=vite&logoColor=white" />
	<img alt="RAG" src="https://img.shields.io/badge/RAG-Context_Grounded-8B1E3F?style=for-the-badge" />
</p>

DecisionAI is a high-energy decision engine that transforms uncertainty into actionable recommendations. It blends retrieval-augmented generation with domain-driven scoring, giving users answers that are not only smart, but explainable.

## Why This Hits Different

- Decision-first architecture, not a generic chatbot wrapper
- Retrieval-enhanced context for grounded outputs
- Transparent criteria mapping and domain scoring
- Built for practical scenarios like laptops, life decisions, and preference trade-offs

![DecisionAI Interface Preview](./decisionai-preview-1.png)

## System Blueprint

### Backend: decisionai

- Spring Boot API for decision orchestration
- Decision engine + domain registry for modular logic
- RAG utilities with vectorization and cosine similarity
- Resource packs for scenario knowledge

### Frontend: decisionai-frontend

- React + Vite experience layer
- User preference capture and scenario prompting
- Structured recommendation presentation

![DecisionAI Recommendation Flow](./decisionai-preview-2.png)

## Quickstart

### 1. Clone

```bash
git clone https://github.com/ishan565/DecisionAI-RAG-GenAI-.git
cd DecisionAI-RAG-GenAI-
```

### 2. Run Backend

```bash
cd decisionai
./mvnw spring-boot:run
```

### 3. Run Frontend

Use a second terminal:

```bash
cd decisionai-frontend
npm install
npm run dev
```

## Project Structure

```text
.
├── decisionai/            # Spring Boot backend + RAG decision core
├── decisionai-frontend/   # React + Vite frontend
├── decisionai-preview-1.png
├── decisionai-preview-2.png
└── decisionai-preview-3.png
```

## Vision Orbit

This project is designed to evolve into a full decision intelligence platform: richer domain packs, stronger retrieval pipelines, and recommendation trails users can trust.

![DecisionAI Full Experience](./decisionai-preview-3.png)

## Author

Crafted by Ishan Gupta to merge practical AI reasoning with product-grade UX.
