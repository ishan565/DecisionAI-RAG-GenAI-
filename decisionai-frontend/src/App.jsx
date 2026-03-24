import { useState } from "react";
import axios from "axios";
import {
    BarChart,
    Bar,
    XAxis,
    YAxis,
    Tooltip,
    ResponsiveContainer,
    RadarChart,
    Radar,
    PolarGrid,
    PolarAngleAxis,
    PolarRadiusAxis
} from "recharts";

const domainCriteria = {
    LAPTOP: ["performance", "battery", "portability", "budget"],
    COLLEGE: [
        "tuitionCost",
        "placementRate",
        "campusLife",
        "location",
        "facultyQuality",
        "researchOpportunities"
    ],
    JOB: [
        "salary",
        "learningOpportunity",
        "workLifeBalance",
        "brandValue",
        "growthPotential",
        "stability"
    ],
    RENT_BUY: [
        "monthlyCost",
        "longTermInvestment",
        "flexibility",
        "maintenanceCost",
        "lifestyleFit",
        "taxBenefits"
    ]
};

/* ---------- Helper to create initial state ---------- */
const createInitialState = (domain) => {
    const weights = {};
    const options = [
        { name: "Option A", criteriaScores: {} },
        { name: "Option B", criteriaScores: {} }
    ];

    domainCriteria[domain].forEach((c) => {
        weights[c] = 3;
        options.forEach((opt) => {
            opt.criteriaScores[c] = 5;
        });
    });

    return { weights, options };
};

function App() {
    const [domain, setDomain] = useState("LAPTOP");
    const initialState = createInitialState("LAPTOP");

    const [weights, setWeights] = useState(initialState.weights);
    const [options, setOptions] = useState(initialState.options);
    const [result, setResult] = useState(null);
    const [scenarios, setScenarios] = useState([]);
    const [scenarioResults, setScenarioResults] = useState([]);

    /* ---------- Handle Domain Change Cleanly ---------- */
    const handleDomainChange = (newDomain) => {
        setDomain(newDomain);
        const newState = createInitialState(newDomain);
        setWeights(newState.weights);
        setOptions(newState.options);
        setResult(null);
        setScenarios([]);
        setScenarioResults([]);

    };



    /* ---------- Radar Data ---------- */
    const radarData =
        result
            ? domainCriteria[domain].map((criterion) => ({
                criterion,
                OptionA: options[0].criteriaScores[criterion],
                OptionB: options[1].criteriaScores[criterion]
            }))
            : [];

    const runScenarios = async () => {
        if (scenarios.length === 0) return;
        console.log("Scenarios:", scenarios);

        if (scenarios.length === 0) {
            alert("Save at least one scenario first.");
            return;
        }
        const response = await axios.post(
            "http://localhost:8080/api/decision/scenario",
            {
                decisionType: domain,
                options: options,
                scenarios: scenarios.map((s) => ({
                    scenarioName: s.name,
                    weights: s.weights
                }))
            }
        );

        setScenarioResults(response.data);
    };

    const scenarioChartData = scenarioResults.map((s) => ({
        name: s.scenarioName,
        confidence: Math.round(s.confidence * 100),
        decision: s.recommendation
    }));


    return (
        <div className="min-h-screen text-white bg-gradient-to-br from-[#0f172a] via-[#020617] to-black">

        <div className="px-8 py-6">
            <div className="mb-10">
                <h1 className="text-5xl py-1 font-bold bg-gradient-to-r from-blue-400 via-cyan-300 to-purple-400 bg-clip-text text-transparent">
                    Decision Intelligence AI
                </h1>

                <p className="text-gray-400 mt-3 text-lg">
                    Simulate real-life decisions using explainable artificial intelligence
                </p>
            </div>



            <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
                    {/* LEFT PANEL */}
                    <div className="lg:col-span-1 bg-white/5 backdrop-blur-xl border border-white/10 shadow-2xl rounded-2xl p-6
">
                        <h2 className="text-xl font-semibold mb-4">Configuration</h2>

                        {/* Domain Selector */}
                        <div className="mb-4">
                            <label className="block mb-2">Decision Type</label>
                            <select
                                value={domain}
                                onChange={(e) => handleDomainChange(e.target.value)}
                                className="w-full bg-gray-800 p-2 rounded"
                            >
                                <option value="LAPTOP">Laptop</option>
                                <option value="COLLEGE">College</option>
                                <option value="JOB">Job</option>
                                <option value="RENT_BUY">Rent vs Buy</option>
                            </select>
                        </div>

                        {/* Weight Sliders */}
                        <div className="mt-6">
                            <h3 className="font-semibold mb-3">Adjust Importance</h3>

                            {domainCriteria[domain].map((criterion) => (
                                <div key={criterion} className="mb-4">
                                    <label className="block text-sm mb-1 capitalize">
                                        {criterion}
                                    </label>

                                    <input
                                        type="range"
                                        min="1"
                                        max="5"
                                        value={weights[criterion]}
                                        onChange={(e) =>
                                            setWeights({
                                                ...weights,
                                                [criterion]: parseInt(e.target.value)
                                            })
                                        }
                                        className="w-full"
                                    />

                                    <p className="text-xs text-gray-400">
                                        Weight: {weights[criterion]}
                                    </p>
                                </div>
                            ))}
                        </div>

                        {/* Option Scores */}
                        <div className="mt-8">
                            <h3 className="font-semibold mb-3">Option Scores</h3>

                            {options.map((option, index) => (
                                <div key={index} className="mb-6">
                                    <input
                                        type="text"
                                        value={option.name}
                                        onChange={(e) => {
                                            const updated = [...options];
                                            updated[index].name = e.target.value;
                                            setOptions(updated);
                                        }}
                                        className="w-full mb-2 p-2 bg-gray-800 rounded"
                                        placeholder="Enter option name"
                                    />


                                    {domainCriteria[domain].map((criterion) => (
                                        <div key={criterion} className="mb-3">
                                            <label className="block text-xs mb-1 capitalize">
                                                {criterion}
                                            </label>

                                            <input
                                                type="range"
                                                min="1"
                                                max="10"
                                                value={option.criteriaScores[criterion]}
                                                onChange={(e) => {
                                                    const updated = [...options];
                                                    updated[index].criteriaScores[criterion] =
                                                        parseInt(e.target.value);
                                                    setOptions(updated);
                                                }}
                                                className="w-full"
                                            />

                                            <p className="text-xs text-gray-400">
                                                Score: {option.criteriaScores[criterion]}
                                            </p>
                                        </div>
                                    ))}
                                </div>
                            ))}
                        </div>
                        <button

                            onClick={async () => {
                                const response = await axios.post(
                                    "http://localhost:8080/api/decision/generic",
                                    {
                                        decisionType: domain,
                                        options: options,
                                        preferences: {
                                            weights: weights
                                        }
                                    }
                                );

                                setResult(response.data);
                            }}
                            className="w-full mt-2 py-2 rounded-xl bg-gradient-to-r from-blue-600 to-cyan-500 hover:scale-[1.02] active:scale-[0.98] transition-all duration-200 shadow-lg shadow-blue-500/20"

                        >
                            Run Decision
                        </button>

                        <button
                            onClick={() =>
                                setScenarios([
                                    ...scenarios,
                                    {
                                        name: `Scenario ${scenarios.length + 1}`,
                                        weights: { ...weights }
                                    }
                                ])
                            }
                            className="w-full mt-2 py-2 rounded-xl bg-gradient-to-r from-blue-600 to-cyan-500 hover:scale-[1.02] active:scale-[0.98] transition-all duration-200 shadow-lg shadow-blue-500/20"

                        >
                            Save Scenario
                        </button>

                        <button
                            onClick={runScenarios}
                            disabled={scenarios.length === 0}
                            className={`w-full py-3 rounded-lg mt-3 ${
                                scenarios.length === 0
                                    ? "bg-gray-600 cursor-not-allowed"
                                    : "bg-orange-600 hover:bg-orange-700"
                            }`}
                        >
                            Run Scenario Analysis
                        </button>


                    </div>

                    {/* RIGHT PANEL */}
                    <div className="lg:col-span-2 bg-white/5 backdrop-blur-xl border border-white/10 shadow-2xl rounded-2xl p-6
">
                        <h2 className="text-xl font-semibold mb-4">Analysis</h2>

                        {result ? (
                            <>
                                <div className="bg-gradient-to-r from-blue-600/20 to-purple-600/20 border border-white/10 p-6 rounded-2xl mb-6">
                                    <p className="text-sm text-gray-400 mb-1">Recommended Option</p>
                                    <h3 className="text-3xl font-bold text-white">
                                        {result.recommendation}
                                    </h3>

                                    <p className="text-gray-400 mt-2">
                                        Dominant factor: <span className="text-cyan-300">{result.dominantFactor}</span>
                                    </p>
                                </div>


                                {/* Confidence */}
                                <div className="mb-8">
                                    <div className="flex justify-between text-sm mb-2">
                                        <span>Confidence Level</span>
                                        <span className="text-cyan-300 font-semibold">
      {(result.confidence * 100).toFixed(1)}%
    </span>
                                    </div>

                                    <div className="w-full h-3 bg-white/10 rounded-full overflow-hidden">
                                        <div
                                            className="h-full bg-gradient-to-r from-cyan-400 to-blue-500 transition-all duration-700"
                                            style={{ width: `${result.confidence * 100}%` }}
                                        />
                                    </div>
                                </div>

                                {/*trade off*/}
                                <div className="bg-white/5 border border-white/10 rounded-xl p-4 mb-6">
                                    <p className="text-sm text-gray-400">Weakness of winner</p>
                                    <p className="text-lg text-amber-300 font-semibold">
                                        {result.weakestFactor}
                                    </p>
                                </div>


                                {/* Score Chart */}
                                <div className="bg-black/30 border border-white/10 rounded-2xl p-4 h-96">

                                <ResponsiveContainer width="100%" height="100%">
                                        <BarChart
                                            data={[
                                                { name: options[0].name, score: result.optionAScore },
                                                { name: options[1].name, score: result.optionBScore }
                                            ]}

                                        >
                                            <XAxis dataKey="name" stroke="#ccc" />
                                            <YAxis stroke="#ccc" />
                                            <Tooltip />
                                            <Bar dataKey="score" fill="#3b82f6" />
                                        </BarChart>
                                    </ResponsiveContainer>
                                </div>

                                {/* Radar Chart */}
                                <div className="bg-black/30 border border-white/10 rounded-2xl p-4 h-80 mt-10">

                                <ResponsiveContainer width="100%" height="100%">
                                        <RadarChart data={radarData}>
                                            <PolarGrid />
                                            <PolarAngleAxis dataKey="criterion" stroke="#ccc" />
                                            <PolarRadiusAxis stroke="#ccc" />
                                            <Radar
                                                name={options[0].name}
                                                dataKey="OptionA"
                                                stroke="#3b82f6"
                                                fill="#3b82f6"
                                                fillOpacity={0.5}
                                            />
                                            <Radar
                                               name={options[1].name}
                                                dataKey="OptionB"
                                                stroke="#10b981"
                                                fill="#10b981"
                                                fillOpacity={0.5}
                                            />
                                            <Tooltip />
                                        </RadarChart>
                                    </ResponsiveContainer>
                                </div>

                                {/* AI Explanation */}
                                <div className="mt-6 text-gray-300 whitespace-pre-line">
                                    {result.explanation}
                                </div>

                                {/* Scenario Comparison Chart */}
                                {scenarioResults.length > 0 && (
                                    <div className="relative mt-12 mb-12 p-[1px] rounded-3xl bg-gradient-to-br from-white/20 via-white/5 to-transparent">
                                        <div className="rounded-3xl backdrop-blur-2xl bg-white/5 border border-white/10 shadow-[0_0_40px_rgba(0,200,255,0.08)] p-6 h-[360px]">


                                            <div className="flex justify-between items-center mb-4">
                                                <h3 className="text-xl font-semibold tracking-wide">
                                                    Scenario Simulation
                                                </h3>
                                                <span className="text-xs text-cyan-300 bg-cyan-400/10 px-3 py-1 rounded-full border border-cyan-400/20">
    What-If Analysis
  </span>
                                            </div>


                                            <ResponsiveContainer width="100%" height="100%">
                                            <BarChart data={scenarioResults}>
                                                <XAxis dataKey="scenarioName" stroke="#ccc" />
                                                <YAxis stroke="#ccc" />
                                                <Tooltip />
                                                <Bar dataKey="confidence" fill="#f59e0b" />
                                            </BarChart>
                                        </ResponsiveContainer>
                                    </div>
                                    </div>
                                )}

                            </>
                        ) : (
                            <p className="text-gray-500">
                                Run decision to see analysis.
                            </p>
                        )}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default App;
