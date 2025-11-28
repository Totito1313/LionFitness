import svgPaths from "./svg-lwpjubuogz";
import { Search, Filter, Dumbbell, Target, ChevronRight, X } from "lucide-react";
import { useState } from "react";

function Frame5() {
  return (
    <div className="basis-0 grow h-full min-h-px min-w-px relative shrink-0">
      <div className="flex flex-col justify-center size-full">
        <div className="box-border content-stretch flex flex-col items-start justify-center px-[8px] py-[4px] relative size-full">
          <p className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] font-bold leading-[normal] relative shrink-0 text-[20px] text-black w-full">Ejercicios</p>
        </div>
      </div>
    </div>
  );
}

function Left() {
  return (
    <div className="basis-0 content-stretch flex gap-[10px] grow items-center min-h-px min-w-px relative shrink-0" data-name="Left">
      <div className="basis-0 flex flex-row grow items-center self-stretch shrink-0">
        <Frame5 />
      </div>
    </div>
  );
}

function Shape() {
  return (
    <div className="absolute left-1/2 size-[24px] top-1/2 translate-x-[-50%] translate-y-[-50%]" data-name="Shape">
      <svg className="block size-full" fill="none" preserveAspectRatio="none" viewBox="0 0 24 24">
        <g id="search">
          <path d={svgPaths.p17fb4ac0} fill="var(--fill-0, black)" id="Vector" stroke="var(--stroke-0, black)" strokeWidth="0.333333" />
        </g>
      </svg>
    </div>
  );
}

function Frame6() {
  return (
    <div className="relative rounded-[40px] shrink-0 size-[50px]">
      <Shape />
    </div>
  );
}

function Shape1() {
  return (
    <div className="absolute left-1/2 size-[24px] top-1/2 translate-x-[-50%] translate-y-[-50%]" data-name="Shape">
      <svg className="block size-full" fill="none" preserveAspectRatio="none" viewBox="0 0 24 24">
        <g id="more">
          <path d={svgPaths.p34a001c0} fill="var(--fill-0, black)" id="Vector" stroke="var(--stroke-0, black)" strokeWidth="0.333333" />
        </g>
      </svg>
    </div>
  );
}

function Frame7() {
  return (
    <div className="relative rounded-[40px] shrink-0 size-[50px]">
      <Shape1 />
    </div>
  );
}

function Right() {
  return (
    <div className="backdrop-blur-md backdrop-filter bg-[rgba(241,241,243,0.5)] box-border content-stretch flex items-center justify-end relative rounded-[25px] shadow-[0px_0px_65px_0px_rgba(0,0,0,0.25)] shrink-0" data-name="Right">
      <Frame6 />
      <Frame7 />
    </div>
  );
}

function TopAppBar() {
  return (
    <div className="absolute box-border content-stretch flex items-center justify-between left-0 px-[18px] py-[12px] top-0 w-[440px]" data-name="Top app bar">
      <div className="absolute flex h-[54px] items-center justify-center left-[calc(50%-0.5px)] top-0 translate-x-[-50%] w-[553px]">
        <div className="flex-none scale-y-[-100%]">
          <div className="bg-gradient-to-b from-[rgba(241,241,243,0)] h-[54px] to-[#f1f1f3] w-[553px]" />
        </div>
      </div>
      <Left />
      <Right />
    </div>
  );
}

function Home() {
  return (
    <div className="absolute inset-0 overflow-clip" data-name="home">
      <div className="absolute aspect-[22/24] bottom-[16.67%] left-[calc(50%-0.21px)] top-[12.5%] translate-x-[-50%]" data-name="Vector">
        <svg className="block size-full" fill="none" preserveAspectRatio="none" viewBox="0 0 13 15">
          <g id="Vector">
            <path clipRule="evenodd" d={svgPaths.p3786d100} fill="var(--fill-0, white)" fillRule="evenodd" />
            <path d={svgPaths.p34240080} stroke="var(--stroke-0, black)" strokeOpacity="0.6" />
          </g>
        </svg>
      </div>
    </div>
  );
}

function Shape2() {
  return (
    <div className="relative shrink-0 size-[20px]" data-name="Shape">
      <Home />
    </div>
  );
}

function Frame() {
  return (
    <div className="relative rounded-[48px] shrink-0 w-full">
      <div className="flex flex-row items-center justify-center overflow-clip rounded-[inherit] size-full">
        <div className="box-border content-stretch flex gap-[10px] items-center justify-center px-[8px] py-[2px] relative w-full">
          <Shape2 />
        </div>
      </div>
    </div>
  );
}

function IconWithLabel() {
  return (
    <div className="content-stretch flex flex-col gap-[2px] items-center relative shrink-0 w-[54px]" data-name="Icon with label 8.5">
      <Frame />
      <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] font-normal leading-[normal] relative shrink-0 text-[12px] text-[rgba(0,0,0,0.6)] text-center w-[84px]">Inicio</p>
    </div>
  );
}

function FloatingNavBarItem() {
  return (
    <div className="box-border content-stretch flex gap-[10px] items-center justify-center overflow-clip px-[12px] py-[4px] relative rounded-[79px] shrink-0" data-name="Floating nav bar item">
      <IconWithLabel />
    </div>
  );
}

function Shape3() {
  return (
    <div className="relative shrink-0 size-[20px]" data-name="Shape">
      <svg className="block size-full" fill="none" preserveAspectRatio="none" viewBox="0 0 20 20">
        <g id="report">
          <path d={svgPaths.p2bb5ab80} id="Vector" stroke="var(--stroke-0, black)" strokeLinecap="round" strokeLinejoin="round" strokeOpacity="0.6" strokeWidth="1.5" />
        </g>
      </svg>
    </div>
  );
}

function Frame1() {
  return (
    <div className="relative rounded-[48px] shrink-0 w-full">
      <div className="flex flex-row items-center justify-center overflow-clip rounded-[inherit] size-full">
        <div className="box-border content-stretch flex gap-[10px] items-center justify-center px-[8px] py-[2px] relative w-full">
          <Shape3 />
        </div>
      </div>
    </div>
  );
}

function IconWithLabel1() {
  return (
    <div className="content-stretch flex flex-col gap-[2px] items-center relative shrink-0 w-[54px]" data-name="Icon with label 8.5">
      <Frame1 />
      <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] font-normal leading-[normal] relative shrink-0 text-[12px] text-[rgba(0,0,0,0.6)] text-center w-[84px]">Estadisticas</p>
    </div>
  );
}

function FloatingNavBarItem1() {
  return (
    <div className="box-border content-stretch flex gap-[10px] items-center justify-center overflow-clip px-[12px] py-[4px] relative rounded-[79px] shrink-0 w-[78px]" data-name="Floating nav bar item">
      <IconWithLabel1 />
    </div>
  );
}

function Shape4() {
  return (
    <div className="relative shrink-0 size-[20px]" data-name="Shape">
      <svg className="block size-full" fill="none" preserveAspectRatio="none" viewBox="0 0 20 20">
        <g id="workout">
          <path clipRule="evenodd" d={svgPaths.p2dbf8600} fill="var(--fill-0, black)" fillOpacity="0.6" fillRule="evenodd" id="Vector" />
        </g>
      </svg>
    </div>
  );
}

function Frame2() {
  return (
    <div className="relative rounded-[48px] shrink-0 w-full">
      <div className="flex flex-row items-center justify-center overflow-clip rounded-[inherit] size-full">
        <div className="box-border content-stretch flex gap-[10px] items-center justify-center px-[8px] py-[2px] relative w-full">
          <Shape4 />
        </div>
      </div>
    </div>
  );
}

function IconWithLabel2() {
  return (
    <div className="content-stretch flex flex-col gap-[2px] items-center relative shrink-0 w-[54px]" data-name="Icon with label 8.5">
      <Frame2 />
      <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] font-normal leading-[normal] relative shrink-0 text-[12px] text-[rgba(0,0,0,0.6)] text-center w-[84px]">Entrenos</p>
    </div>
  );
}

function FloatingNavBarItem2() {
  return (
    <div className="box-border content-stretch flex gap-[10px] items-center justify-center overflow-clip px-[12px] py-[4px] relative rounded-[79px] shrink-0 w-[78px]" data-name="Floating nav bar item">
      <IconWithLabel2 />
    </div>
  );
}

function Shape5() {
  return (
    <div className="relative shrink-0 size-[20px]" data-name="Shape">
      <svg className="block size-full" fill="none" preserveAspectRatio="none" viewBox="0 0 20 20">
        <g id="height">
          <path clipRule="evenodd" d={svgPaths.p1c1e7c80} fill="var(--fill-0, black)" fillOpacity="0.6" fillRule="evenodd" id="Vector" />
          <path d={svgPaths.p38635680} id="Vector_2" stroke="var(--stroke-0, black)" strokeLinecap="round" strokeOpacity="0.6" strokeWidth="1.5" />
        </g>
      </svg>
    </div>
  );
}

function Frame3() {
  return (
    <div className="relative rounded-[48px] shrink-0 w-full">
      <div className="flex flex-row items-center justify-center overflow-clip rounded-[inherit] size-full">
        <div className="box-border content-stretch flex gap-[10px] items-center justify-center px-[8px] py-[2px] relative w-full">
          <Shape5 />
        </div>
      </div>
    </div>
  );
}

function IconWithLabel3() {
  return (
    <div className="content-stretch flex flex-col gap-[2px] items-center relative shrink-0 w-[54px]" data-name="Icon with label 8.5">
      <Frame3 />
      <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] font-normal leading-[normal] relative shrink-0 text-[12px] text-[rgba(0,0,0,0.6)] text-center w-[84px]">Medidas</p>
    </div>
  );
}

function FloatingNavBarItem3() {
  return (
    <div className="box-border content-stretch flex gap-[10px] items-center justify-center overflow-clip px-[12px] py-[4px] relative rounded-[79px] shrink-0 w-[78px]" data-name="Floating nav bar item">
      <IconWithLabel3 />
    </div>
  );
}

function Shape6() {
  return (
    <div className="relative shrink-0 size-[20px]" data-name="Shape">
      <svg className="block size-full" fill="none" preserveAspectRatio="none" viewBox="0 0 20 20">
        <g id="drawer">
          <path d={svgPaths.p20e14080} fill="var(--fill-0, white)" id="Vector" stroke="var(--stroke-0, black)" />
        </g>
      </svg>
    </div>
  );
}

function Frame4() {
  return (
    <div className="relative rounded-[48px] shrink-0 w-full">
      <div className="flex flex-row items-center justify-center overflow-clip rounded-[inherit] size-full">
        <div className="box-border content-stretch flex gap-[10px] items-center justify-center px-[8px] py-[2px] relative w-full">
          <Shape6 />
        </div>
      </div>
    </div>
  );
}

function IconWithLabel4() {
  return (
    <div className="content-stretch flex flex-col gap-[2px] items-center relative shrink-0 w-[54px]" data-name="Icon with label 8.5">
      <Frame4 />
      <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] font-normal leading-[normal] relative shrink-0 text-[12px] text-black text-center w-[84px]">Menu</p>
    </div>
  );
}

function FloatingNavBarItem4() {
  return (
    <div className="box-border content-stretch flex gap-[10px] items-center justify-center overflow-clip px-[12px] py-[4px] relative rounded-[79px] shrink-0 w-[78px]" data-name="Floating nav bar item">
      <IconWithLabel4 />
    </div>
  );
}

function FloatingNav() {
  return (
    <div className="backdrop-blur-[14px] backdrop-filter bg-[rgba(255,255,255,0.6)] box-border content-stretch flex items-start max-w-[396px] overflow-clip pl-[6px] pr-[14px] py-[5px] relative rounded-[79px] shadow-[0px_7px_16.2px_0px_rgba(0,0,0,0.25)] shrink-0" data-name="Floating nav">
      <FloatingNavBarItem />
      <FloatingNavBarItem1 />
      <FloatingNavBarItem2 />
      <FloatingNavBarItem3 />
      <FloatingNavBarItem4 />
    </div>
  );
}

function FloatingNav1() {
  return (
    <div className="absolute bottom-[21px] content-stretch flex flex-col gap-[10px] items-center justify-center left-1/2 translate-x-[-50%]" data-name="Floating Nav">
      <div className="absolute bg-gradient-to-b from-[rgba(241,241,243,0)] h-[134px] left-[calc(50%-0.5px)] to-[#f1f1f3] top-1/2 translate-x-[-50%] translate-y-[-50%] w-[533px]" />
      <FloatingNav />
    </div>
  );
}

// Datos de ejercicios
const exercises = [
  { id: 1, name: "Press de Banca", muscle: "Pecho", difficulty: "Intermedio", equipment: "Barra", color: "from-[#ff6b6b] to-[#ee5a6f]", type: "Compuesto" },
  { id: 2, name: "Sentadilla", muscle: "Piernas", difficulty: "Intermedio", equipment: "Barra", color: "from-[#4c6ef5] to-[#5f3dc4]", type: "Compuesto" },
  { id: 3, name: "Peso Muerto", muscle: "Espalda", difficulty: "Avanzado", equipment: "Barra", color: "from-[#20c997] to-[#12b886]", type: "Compuesto" },
  { id: 4, name: "Press Militar", muscle: "Hombros", difficulty: "Intermedio", equipment: "Barra", color: "from-[#ffd43b] to-[#fab005]", type: "Compuesto" },
  { id: 5, name: "Curl de Bíceps", muscle: "Brazos", difficulty: "Principiante", equipment: "Mancuernas", color: "from-[#845ef7] to-[#7048e8]", type: "Aislamiento" },
  { id: 6, name: "Extensión de Tríceps", muscle: "Brazos", difficulty: "Principiante", equipment: "Polea", color: "from-[#845ef7] to-[#7048e8]", type: "Aislamiento" },
  { id: 7, name: "Dominadas", muscle: "Espalda", difficulty: "Avanzado", equipment: "Peso Corporal", color: "from-[#20c997] to-[#12b886]", type: "Compuesto" },
  { id: 8, name: "Fondos en Paralelas", muscle: "Pecho", difficulty: "Intermedio", equipment: "Peso Corporal", color: "from-[#ff6b6b] to-[#ee5a6f]", type: "Compuesto" },
  { id: 9, name: "Curl de Pierna", muscle: "Piernas", difficulty: "Principiante", equipment: "Máquina", color: "from-[#4c6ef5] to-[#5f3dc4]", type: "Aislamiento" },
  { id: 10, name: "Elevaciones Laterales", muscle: "Hombros", difficulty: "Principiante", equipment: "Mancuernas", color: "from-[#ffd43b] to-[#fab005]", type: "Aislamiento" },
  { id: 11, name: "Press Inclinado", muscle: "Pecho", difficulty: "Intermedio", equipment: "Barra", color: "from-[#ff6b6b] to-[#ee5a6f]", type: "Compuesto" },
  { id: 12, name: "Remo con Barra", muscle: "Espalda", difficulty: "Intermedio", equipment: "Barra", color: "from-[#20c997] to-[#12b886]", type: "Compuesto" },
  { id: 13, name: "Zancadas", muscle: "Piernas", difficulty: "Intermedio", equipment: "Mancuernas", color: "from-[#4c6ef5] to-[#5f3dc4]", type: "Compuesto" },
  { id: 14, name: "Face Pulls", muscle: "Hombros", difficulty: "Principiante", equipment: "Polea", color: "from-[#ffd43b] to-[#fab005]", type: "Aislamiento" },
  { id: 15, name: "Plancha", muscle: "Core", difficulty: "Principiante", equipment: "Peso Corporal", color: "from-[#ff8787] to-[#ff6b6b]", type: "Isométrico" },
];

const muscleGroups = ["Todos", "Pecho", "Espalda", "Piernas", "Hombros", "Brazos", "Core"];

function SearchBar({ searchTerm, setSearchTerm }: { searchTerm: string; setSearchTerm: (term: string) => void }) {
  return (
    <div className="backdrop-blur-md backdrop-filter bg-[rgba(255,255,255,0.7)] rounded-[20px] shadow-[0px_4px_16px_0px_rgba(0,0,0,0.08)] p-[14px] mx-[18px] mb-[16px] flex items-center gap-[12px]">
      <Search className="size-[20px] text-[rgba(0,0,0,0.4)]" />
      <input
        type="text"
        placeholder="Buscar ejercicios..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
        className="flex-1 bg-transparent outline-none font-['One_UI_Sans_APP_VF:500_Medium',sans-serif] text-[15px] text-black placeholder:text-[rgba(0,0,0,0.4)]"
      />
      {searchTerm && (
        <button onClick={() => setSearchTerm("")} className="size-[24px] rounded-full bg-[rgba(0,0,0,0.05)] flex items-center justify-center hover:bg-[rgba(0,0,0,0.1)] transition-colors">
          <X className="size-[14px] text-[rgba(0,0,0,0.6)]" />
        </button>
      )}
    </div>
  );
}

function FilterChip({ label, active, onClick }: { label: string; active: boolean; onClick: () => void }) {
  return (
    <button
      onClick={onClick}
      className={`font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[13px] px-[16px] py-[10px] rounded-[20px] transition-all whitespace-nowrap ${
        active 
          ? "bg-[#4c6ef5] text-white shadow-[0px_4px_12px_0px_rgba(76,110,245,0.3)]" 
          : "bg-[rgba(0,0,0,0.05)] text-[rgba(0,0,0,0.6)] hover:bg-[rgba(0,0,0,0.08)]"
      }`}
    >
      {label}
    </button>
  );
}

function FilterBar({ activeFilter, setActiveFilter }: { activeFilter: string; setActiveFilter: (filter: string) => void }) {
  return (
    <div className="mb-[20px]">
      <div className="flex items-center gap-[8px] px-[18px] mb-[12px]">
        <Filter className="size-[18px] text-[rgba(0,0,0,0.6)]" />
        <p className="font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[14px] text-[rgba(0,0,0,0.7)]">Filtrar por grupo muscular</p>
      </div>
      <div className="flex gap-[8px] overflow-x-auto px-[18px] pb-[4px] scrollbar-hide" style={{ scrollbarWidth: 'none', msOverflowStyle: 'none' }}>
        {muscleGroups.map((group) => (
          <FilterChip
            key={group}
            label={group}
            active={activeFilter === group}
            onClick={() => setActiveFilter(group)}
          />
        ))}
      </div>
    </div>
  );
}

function DifficultyBadge({ level }: { level: string }) {
  const colors = {
    "Principiante": "bg-[#12b886] text-white",
    "Intermedio": "bg-[#fab005] text-white",
    "Avanzado": "bg-[#ff6b6b] text-white"
  };
  
  return (
    <span className={`font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[10px] px-[8px] py-[3px] rounded-full ${colors[level as keyof typeof colors]}`}>
      {level}
    </span>
  );
}

function ExerciseCard({ exercise }: { exercise: typeof exercises[0] }) {
  return (
    <div className="backdrop-blur-md backdrop-filter bg-[rgba(255,255,255,0.7)] rounded-[20px] shadow-[0px_4px_16px_0px_rgba(0,0,0,0.08)] p-[16px] mb-[12px] flex items-center gap-[14px] hover:shadow-[0px_6px_20px_0px_rgba(0,0,0,0.12)] transition-all">
      {/* Icono con gradiente */}
      <div className={`size-[56px] rounded-[16px] bg-gradient-to-br ${exercise.color} flex items-center justify-center shadow-md flex-shrink-0`}>
        <Dumbbell className="size-[26px] text-white" />
      </div>

      {/* Información del ejercicio */}
      <div className="flex-1 min-w-0">
        <h3 className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[15px] text-black mb-[4px]">{exercise.name}</h3>
        <div className="flex items-center gap-[8px] mb-[6px]">
          <div className="flex items-center gap-[4px]">
            <Target className="size-[12px] text-[rgba(0,0,0,0.5)]" />
            <span className="font-['One_UI_Sans_APP_VF:500_Medium',sans-serif] text-[12px] text-[rgba(0,0,0,0.6)]">{exercise.muscle}</span>
          </div>
          <span className="text-[rgba(0,0,0,0.3)]">•</span>
          <span className="font-['One_UI_Sans_APP_VF:500_Medium',sans-serif] text-[12px] text-[rgba(0,0,0,0.6)]">{exercise.equipment}</span>
        </div>
        <div className="flex items-center gap-[6px]">
          <DifficultyBadge level={exercise.difficulty} />
          <span className="font-['One_UI_Sans_APP_VF:500_Medium',sans-serif] text-[11px] px-[8px] py-[3px] rounded-full bg-[rgba(0,0,0,0.05)] text-[rgba(0,0,0,0.6)]">
            {exercise.type}
          </span>
        </div>
      </div>

      {/* Botón de acción */}
      <button className="size-[40px] rounded-full bg-[rgba(76,110,245,0.1)] flex items-center justify-center hover:bg-[rgba(76,110,245,0.2)] transition-colors flex-shrink-0">
        <ChevronRight className="size-[20px] text-[#4c6ef5]" />
      </button>
    </div>
  );
}

function ExerciseList({ exercises }: { exercises: typeof exercises }) {
  return (
    <div className="px-[18px] mb-[120px]">
      <div className="flex items-center justify-between mb-[14px]">
        <p className="font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[14px] text-[rgba(0,0,0,0.6)]">
          {exercises.length} ejercicios encontrados
        </p>
      </div>
      {exercises.length === 0 ? (
        <div className="flex flex-col items-center justify-center py-[60px]">
          <div className="size-[80px] rounded-full bg-[rgba(0,0,0,0.03)] flex items-center justify-center mb-[16px]">
            <Search className="size-[36px] text-[rgba(0,0,0,0.3)]" />
          </div>
          <p className="font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[16px] text-[rgba(0,0,0,0.4)] mb-[6px]">No se encontraron ejercicios</p>
          <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] text-[13px] text-[rgba(0,0,0,0.3)] text-center">Intenta con otros filtros o términos de búsqueda</p>
        </div>
      ) : (
        exercises.map((exercise) => (
          <ExerciseCard key={exercise.id} exercise={exercise} />
        ))
      )}
    </div>
  );
}

function ScrollContent() {
  const [searchTerm, setSearchTerm] = useState("");
  const [activeFilter, setActiveFilter] = useState("Todos");

  // Filtrar ejercicios
  const filteredExercises = exercises.filter((exercise) => {
    const matchesSearch = exercise.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
                         exercise.muscle.toLowerCase().includes(searchTerm.toLowerCase()) ||
                         exercise.equipment.toLowerCase().includes(searchTerm.toLowerCase());
    const matchesFilter = activeFilter === "Todos" || exercise.muscle === activeFilter;
    return matchesSearch && matchesFilter;
  });

  return (
    <div className="absolute left-0 top-[78px] w-full overflow-y-auto" style={{ height: 'calc(100% - 78px)' }}>
      <SearchBar searchTerm={searchTerm} setSearchTerm={setSearchTerm} />
      <FilterBar activeFilter={activeFilter} setActiveFilter={setActiveFilter} />
      <ExerciseList exercises={filteredExercises} />
    </div>
  );
}

export default function Ejercicios() {
  return (
    <div className="bg-[#f1f1f3] overflow-clip relative rounded-[20px] size-full" data-name="Ejercicios">
      <TopAppBar />
      <ScrollContent />
      <FloatingNav1 />
    </div>
  );
}