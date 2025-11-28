import svgPaths from "./svg-ffgrqmr6yx";
import { Trophy, Timer, Dumbbell, ChevronRight, Heart, MessageCircle, Share2, MoreVertical } from "lucide-react";
import { useRef } from "react";

function Shape() {
  return (
    <div className="absolute left-1/2 size-[24px] top-1/2 translate-x-[-50%] translate-y-[-50%]" data-name="Shape">
      <svg className="block size-full" fill="none" preserveAspectRatio="none" viewBox="0 0 24 24">
        <g id="drawer">
          <path d={svgPaths.p21dbd80} fill="var(--fill-0, black)" id="Vector" stroke="var(--stroke-0, black)" />
        </g>
      </svg>
    </div>
  );
}

function Frame5() {
  return (
    <div className="backdrop-blur-md backdrop-filter bg-[rgba(241,241,243,0.5)] relative rounded-[40px] shadow-[0px_0px_65px_0px_rgba(0,0,0,0.25)] shrink-0 size-[50px]">
      <Shape />
    </div>
  );
}

function Frame6() {
  return (
    <div className="basis-0 grow h-full min-h-px min-w-px relative shrink-0">
      <div className="flex flex-col justify-center size-full">
        <div className="box-border content-stretch flex flex-col items-start justify-center px-[8px] py-[4px] relative size-full">
          <p className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] font-bold leading-[normal] relative shrink-0 text-[20px] text-black w-full">Entrenamientos</p>
        </div>
      </div>
    </div>
  );
}

function Left() {
  return (
    <div className="basis-0 content-stretch flex gap-[10px] grow items-center min-h-px min-w-px relative shrink-0" data-name="Left">
      <Frame5 />
      <div className="basis-0 flex flex-row grow items-center self-stretch shrink-0">
        <Frame6 />
      </div>
    </div>
  );
}

function Shape1() {
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

function Frame7() {
  return (
    <div className="relative rounded-[40px] shrink-0 size-[50px]">
      <Shape1 />
    </div>
  );
}

function Shape2() {
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

function Frame8() {
  return (
    <div className="relative rounded-[40px] shrink-0 size-[50px]">
      <Shape2 />
    </div>
  );
}

function Right() {
  return (
    <div className="backdrop-blur-md backdrop-filter bg-[rgba(241,241,243,0.5)] box-border content-stretch flex items-center justify-end relative rounded-[25px] shadow-[0px_0px_65px_0px_rgba(0,0,0,0.25)] shrink-0" data-name="Right">
      <Frame7 />
      <Frame8 />
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

function Shape3() {
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
          <Shape3 />
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

function Shape4() {
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
          <Shape4 />
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

function Shape5() {
  return (
    <div className="relative shrink-0 size-[20px]" data-name="Shape">
      <svg className="block size-full" fill="none" preserveAspectRatio="none" viewBox="0 0 20 20">
        <g id="workout">
          <path d={svgPaths.p2b29ab00} fill="var(--fill-0, black)" id="Vector" stroke="var(--stroke-0, black)" />
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
          <Shape5 />
        </div>
      </div>
    </div>
  );
}

function IconWithLabel2() {
  return (
    <div className="content-stretch flex flex-col gap-[2px] items-center relative shrink-0 w-[54px]" data-name="Icon with label 8.5">
      <Frame2 />
      <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] font-normal leading-[normal] relative shrink-0 text-[12px] text-black text-center w-[84px]">Entrenos</p>
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

function Shape6() {
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
          <Shape6 />
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

function Shape7() {
  return (
    <div className="relative shrink-0 size-[20px]" data-name="Shape">
      <svg className="block size-full" fill="none" preserveAspectRatio="none" viewBox="0 0 20 20">
        <g id="drawer">
          <g id="Vector">
            <path d={svgPaths.p9727780} fill="var(--fill-0, white)" />
            <path d={svgPaths.p20e14080} stroke="var(--stroke-0, black)" strokeOpacity="0.6" />
          </g>
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
          <Shape7 />
        </div>
      </div>
    </div>
  );
}

function IconWithLabel4() {
  return (
    <div className="content-stretch flex flex-col gap-[2px] items-center relative shrink-0 w-[54px]" data-name="Icon with label 8.5">
      <Frame4 />
      <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] font-normal leading-[normal] relative shrink-0 text-[12px] text-[rgba(0,0,0,0.6)] text-center w-[84px]">Menu</p>
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

// Datos de entrenamientos destacados
const featuredWorkouts = [
  { 
    id: 1, 
    title: "Mayor Peso", 
    workout: "Piernas Destruidas", 
    value: "24,899.9 kg", 
    subtitle: "Volumen total",
    icon: Trophy,
    color: "from-[#ffd43b] to-[#fab005]"
  },
  { 
    id: 2, 
    title: "Mayor Duración", 
    workout: "Cardio Extremo", 
    value: "2h 15min", 
    subtitle: "Tiempo récord",
    icon: Timer,
    color: "from-[#4c6ef5] to-[#5f3dc4]"
  },
  { 
    id: 3, 
    title: "Más Completo", 
    workout: "Full Body", 
    value: "45 ejercicios", 
    subtitle: "En una sesión",
    icon: Dumbbell,
    color: "from-[#ff6b6b] to-[#ee5a6f]"
  },
];

// Datos del historial de entrenamientos
const workoutHistory = [
  {
    id: 1,
    user: "alanjose2013",
    timeAgo: "hace 4 días",
    title: "Piernas destruidas",
    stats: {
      time: "1h 58min",
      volume: "24,899.9 kg",
      records: 9,
      lpm: 114
    },
    exercises: [
      { name: "4 series Curl de Pierna Sentado", image: "gym equipment curl" },
      { name: "4 series Aducción de Caderas", image: "gym equipment adduction" },
      { name: "4 series Abducción de Caderas", image: "gym equipment abduction" }
    ],
    moreCount: 4
  },
  {
    id: 2,
    user: "alanjose2013",
    timeAgo: "hace 5 días",
    title: "Pecho y Espalda La nota",
    stats: {
      time: "1h 44min",
      volume: "16,814.3 kg",
      records: 6,
      lpm: 85
    },
    exercises: [
      { name: "4 series Press de Banca Inclinado (Barra)", image: "gym equipment press" },
      { name: "4 series Aperturas (Máquina)", image: "gym equipment fly" },
      { name: "4 series Press de Pecho (Máquina)", image: "gym equipment chest" }
    ],
    moreCount: 3
  },
  {
    id: 3,
    user: "alanjose2013",
    timeAgo: "hace 7 días",
    title: "Hombros y Brazos",
    stats: {
      time: "1h 32min",
      volume: "12,450.5 kg",
      records: 4,
      lpm: 92
    },
    exercises: [
      { name: "4 series Press Militar (Barra)", image: "gym equipment press" },
      { name: "4 series Elevaciones Laterales", image: "gym equipment lateral" },
      { name: "4 series Curl de Bíceps", image: "gym equipment curl" }
    ],
    moreCount: 5
  }
];

function FeaturedWorkoutCard({ workout }: { workout: typeof featuredWorkouts[0] }) {
  const Icon = workout.icon;
  
  return (
    <div className="backdrop-blur-md backdrop-filter bg-[rgba(255,255,255,0.7)] min-w-[260px] rounded-[24px] shadow-[0px_4px_20px_0px_rgba(0,0,0,0.1)] p-[18px] flex-shrink-0">
      <div className="flex items-start justify-between mb-[12px]">
        <div className={`size-[48px] rounded-full bg-gradient-to-br ${workout.color} flex items-center justify-center shadow-lg`}>
          <Icon className="size-[24px] text-white" />
        </div>
        <div className="bg-[rgba(0,0,0,0.05)] rounded-full px-[10px] py-[4px]">
          <p className="font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[11px] text-[rgba(0,0,0,0.6)]">{workout.title}</p>
        </div>
      </div>
      <h3 className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[16px] text-black mb-[4px]">{workout.workout}</h3>
      <p className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[24px] text-black mb-[2px]">{workout.value}</p>
      <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] text-[12px] text-[rgba(0,0,0,0.5)]">{workout.subtitle}</p>
    </div>
  );
}

function FeaturedCarousel() {
  const scrollRef = useRef<HTMLDivElement>(null);

  return (
    <div className="mb-[20px]">
      <div className="flex items-center justify-between px-[18px] mb-[12px]">
        <h2 className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[18px] text-black">Destacados</h2>
        <ChevronRight className="size-[20px] text-[rgba(0,0,0,0.4)]" />
      </div>
      <div 
        ref={scrollRef}
        className="flex gap-[12px] overflow-x-auto px-[18px] pb-[4px] scrollbar-hide"
        style={{ scrollbarWidth: 'none', msOverflowStyle: 'none' }}
      >
        {featuredWorkouts.map((workout) => (
          <FeaturedWorkoutCard key={workout.id} workout={workout} />
        ))}
      </div>
    </div>
  );
}

function WorkoutHistoryItem({ workout }: { workout: typeof workoutHistory[0] }) {
  return (
    <div className="backdrop-blur-md backdrop-filter bg-[rgba(255,255,255,0.7)] rounded-[24px] shadow-[0px_4px_20px_0px_rgba(0,0,0,0.1)] p-[18px] mb-[16px]">
      {/* Header con usuario */}
      <div className="flex items-center justify-between mb-[16px]">
        <div className="flex items-center gap-[12px]">
          <div className="size-[44px] rounded-full bg-gradient-to-br from-[#4c6ef5] to-[#845ef7] flex items-center justify-center">
            <span className="font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-white text-[16px]">AJ</span>
          </div>
          <div>
            <p className="font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[14px] text-black">{workout.user}</p>
            <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] text-[12px] text-[rgba(0,0,0,0.5)]">{workout.timeAgo}</p>
          </div>
        </div>
        <button className="size-[32px] rounded-full hover:bg-[rgba(0,0,0,0.05)] flex items-center justify-center transition-colors">
          <MoreVertical className="size-[20px] text-[rgba(0,0,0,0.6)]" />
        </button>
      </div>

      {/* Título del entrenamiento */}
      <h3 className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[20px] text-black mb-[14px]">{workout.title}</h3>

      {/* Estadísticas */}
      <div className="grid grid-cols-4 gap-[8px] mb-[16px]">
        <div className="bg-[rgba(0,0,0,0.03)] rounded-[12px] p-[10px]">
          <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] text-[10px] text-[rgba(0,0,0,0.5)] mb-[2px]">Tiempo</p>
          <p className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[13px] text-black">{workout.stats.time}</p>
        </div>
        <div className="bg-[rgba(0,0,0,0.03)] rounded-[12px] p-[10px]">
          <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] text-[10px] text-[rgba(0,0,0,0.5)] mb-[2px]">Volumen</p>
          <p className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[13px] text-black">{workout.stats.volume}</p>
        </div>
        <div className="bg-[rgba(0,0,0,0.03)] rounded-[12px] p-[10px] flex flex-col items-center justify-center">
          <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] text-[10px] text-[rgba(0,0,0,0.5)] mb-[2px]">Récords</p>
          <div className="flex items-center gap-[4px]">
            <span className="text-[16px]">🏆</span>
            <p className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[13px] text-black">{workout.stats.records}</p>
          </div>
        </div>
        <div className="bg-[rgba(0,0,0,0.03)] rounded-[12px] p-[10px] flex flex-col items-center justify-center">
          <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] text-[10px] text-[rgba(0,0,0,0.5)] mb-[2px]">LPM</p>
          <div className="flex items-center gap-[4px]">
            <span className="text-[16px]">❤️</span>
            <p className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[13px] text-black">{workout.stats.lpm}</p>
          </div>
        </div>
      </div>

      {/* Ejercicios */}
      <div className="space-y-[10px] mb-[12px]">
        {workout.exercises.map((exercise, idx) => (
          <div key={idx} className="flex items-center gap-[12px] bg-[rgba(0,0,0,0.02)] rounded-[14px] p-[10px]">
            <div className="size-[40px] rounded-full bg-[rgba(76,110,245,0.1)] flex items-center justify-center">
              <Dumbbell className="size-[20px] text-[#4c6ef5]" />
            </div>
            <p className="font-['One_UI_Sans_APP_VF:500_Medium',sans-serif] text-[13px] text-black flex-1">{exercise.name}</p>
          </div>
        ))}
        {workout.moreCount > 0 && (
          <button className="w-full text-center py-[8px]">
            <p className="font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[13px] text-[#4c6ef5]">
              Ver {workout.moreCount} más ejercicios
            </p>
          </button>
        )}
      </div>

      {/* Acciones */}
      <div className="flex items-center gap-[16px] pt-[12px] border-t border-[rgba(0,0,0,0.06)]">
        <button className="flex items-center gap-[6px] hover:bg-[rgba(0,0,0,0.03)] rounded-[12px] px-[12px] py-[8px] transition-colors">
          <Heart className="size-[20px] text-[rgba(0,0,0,0.6)]" />
          <span className="font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[13px] text-[rgba(0,0,0,0.6)]">Me gusta</span>
        </button>
        <button className="flex items-center gap-[6px] hover:bg-[rgba(0,0,0,0.03)] rounded-[12px] px-[12px] py-[8px] transition-colors">
          <MessageCircle className="size-[20px] text-[rgba(0,0,0,0.6)]" />
          <span className="font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[13px] text-[rgba(0,0,0,0.6)]">Comentar</span>
        </button>
        <button className="flex items-center gap-[6px] hover:bg-[rgba(0,0,0,0.03)] rounded-[12px] px-[12px] py-[8px] transition-colors ml-auto">
          <Share2 className="size-[20px] text-[rgba(0,0,0,0.6)]" />
        </button>
      </div>
    </div>
  );
}

function WorkoutHistory() {
  return (
    <div className="px-[18px]">
      <div className="flex items-center justify-between mb-[16px]">
        <h2 className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[18px] text-black">Historial</h2>
        <button className="font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[13px] text-[#4c6ef5]">Ver todo</button>
      </div>
      <div className="mb-[120px]">
        {workoutHistory.map((workout) => (
          <WorkoutHistoryItem key={workout.id} workout={workout} />
        ))}
      </div>
    </div>
  );
}

function ScrollContent() {
  return (
    <div className="absolute left-0 top-[78px] w-full overflow-y-auto" style={{ height: 'calc(100% - 78px)' }}>
      <FeaturedCarousel />
      <WorkoutHistory />
    </div>
  );
}

export default function Entrenamientos() {
  return (
    <div className="bg-[#f1f1f3] overflow-clip relative rounded-[20px] size-full" data-name="Entrenamientos">
      <TopAppBar />
      <ScrollContent />
      <FloatingNav1 />
    </div>
  );
}