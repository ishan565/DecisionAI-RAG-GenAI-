# DecisionAI: RAG x GenAI Decision Studio

Welcome to DecisionAI, an intelligent decision-making playground where retrieval-augmented generation meets practical everyday choices. This project fuses a Spring Boot reasoning engine with a modern React interface to transform vague questions into structured, context-aware recommendations.

## What This Project Feels Like

DecisionAI is not just another chatbot wrapper. It is a guided decision system that:
- interprets user intent,
- pulls domain context from local knowledge resources,
- scores options with transparent criteria,
- and returns recommendations that feel sharp, explainable, and useful.

From laptop picks to life scenarios, it turns indecision into confident action.

![DecisionAI Preview 1](./Screenshot%202026-03-24%20at%2016.39.39.PNG)

## Core Stack

- Backend: Java + Spring Boot
- AI Layer: OpenAI-powered generation with local RAG support
- Retrieval Utilities: vectorization + cosine similarity utilities
- Frontend: React + Vite + Tailwind CSS
- Build Tools: Maven (backend), npm (frontend)

## Architecture at a Glance

The system is split into two focused surfaces:
- decisionai: backend API, decision engine, model classes, retrieval logic, domain registry
- decisionai-frontend: responsive UI for capturing user preferences and rendering results

Inside the backend, domain packs and criteria mapping provide a clean abstraction that keeps recommendations modular and expandable.

![DecisionAI Preview 2](./Screenshot%202026-03-24%20at%2016.40.13.PNG)

## Why It Stands Out

- Explainable recommendation flow instead of random text generation
- Retrieval-aware context injection for grounded outputs
- Domain-driven structure for easy extension
- Practical real-world scenarios over toy prompts

## Local Setup

### 1) Clone

```bash
git clone https://github.com/ishan565/DecisionAI-RAG-GenAI-.git
cd DecisionAI-RAG-GenAI-
```

### 2) Run Backend

```bash
cd decisionai
./mvnw spring-boot:run
```

### 3) Run Frontend

Open a second terminal:

```bash
cd decisionai-frontend
npm install
npm run dev
```

## Vision

DecisionAI is engineered as a launchpad for smarter, evidence-backed decision systems. The long-term direction is simple: deeper retrieval, richer domain packs, and recommendations that are not only intelligent, but unforgettable.

![DecisionAI Preview 3](./Screenshot%202026-03-24%20at%2016.40.30.PNG)

## Author

Built by Ishan Gupta with a focus on practical AI, clean architecture, and product-grade user experience.
