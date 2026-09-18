package com.example.data

enum class SceneCategory(val titleEn: String, val titleTa: String) {
  INTRODUCTION("Introduction", "அறிமுகம்"),
  PDF_FUNDAMENTALS("PDF Fundamentals", "PDF அடிப்படைகள்"),
  WEB_FOUNDATIONS("HTML & CSS", "HTML மற்றும் CSS"),
  EPUB_CONCEPTS("What is EPUB?", "EPUB அறிமுகம்"),
  EPUB_PACKAGE("EPUB Package Deep Dive", "EPUB Package கட்டமைப்பு"),
  WORKFLOW_ACCESSIBILITY("Workflow & Accessibility", "Workflow & Accessibility"),
  RECAP_QUIZ("Recap & Quiz", "சுருக்கம் & வினாடி-வினா")
}

data class TutorialScene(
  val id: Int,
  val titleEn: String,
  val titleTa: String,
  val durationSeconds: Int,
  val durationDisplay: String,
  val onScreenHeading: String,
  val onScreenSubheading: String,
  val voiceoverTamil: String,
  val summaryNotes: String,
  val category: SceneCategory,
  val keyPoints: List<String>,
  val codeSnippet: String = "",
  val codeType: String = "" // "html", "css", "xml", "tree"
)

data class QuizQuestion(
  val id: Int,
  val questionTa: String,
  val questionEn: String,
  val options: List<String>,
  val correctIndex: Int,
  val explanationTa: String,
  val explanationEn: String
)

data class EpubFileItem(
  val path: String,
  val name: String,
  val extension: String,
  val purposeTa: String,
  val purposeEn: String,
  val codeSnippet: String,
  val isDirectory: Boolean = false,
  val level: Int = 0
)

data class GlossaryItem(
  val term: String,
  val fullForm: String,
  val definitionTa: String,
  val definitionEn: String,
  val iconType: String
)

object TutorialRepository {

  val scenes: List<TutorialScene> = listOf(
    TutorialScene(
      id = 1,
      titleEn = "Introduction to Digital Publishing",
      titleTa = "அறிமுகம் — PDF முதல் EPUB வரை",
      durationSeconds = 40,
      durationDisplay = "0:40",
      onScreenHeading = "PDF → HTML → CSS → EPUB",
      onScreenSubheading = "Beginner Friendly Digital Publishing Tutorial",
      voiceoverTamil = "வணக்கம்! இந்த tutorial-ல் PDF, HTML, CSS மற்றும் EPUB என்றால் என்ன என்பதை beginner level-ல் இருந்து கற்றுக்கொள்ளப் போகிறோம்.\n\nஒரு PDF document எப்படி structure செய்யப்பட்ட digital book ஆக மாற்றப்படுகிறது என்பதையும் பார்க்கப் போகிறோம்.\n\nஇறுதியில் ஒரு complete EPUB package-க்குள் என்னென்ன files இருக்கும் என்பதையும் practical example மூலம் பார்க்கலாம்.",
      summaryNotes = "Welcome! In this tutorial, we will learn what PDF, HTML, CSS and EPUB are from beginner level. We will see how a PDF document transforms into a structured digital book and explore the internal files of a complete EPUB package.",
      category = SceneCategory.INTRODUCTION,
      keyPoints = listOf(
        "PDF = Fixed print-ready format",
        "HTML = Structured content foundation",
        "CSS = Visual styling and presentation",
        "EPUB = Standard reflowable digital book package"
      )
    ),
    TutorialScene(
      id = 2,
      titleEn = "What is PDF?",
      titleTa = "PDF என்றால் என்ன?",
      durationSeconds = 75,
      durationDisplay = "1:15",
      onScreenHeading = "PDF = Portable Document Format",
      onScreenSubheading = "PDF = Page-oriented document",
      voiceoverTamil = "முதலில் PDF என்றால் என்ன என்று பார்க்கலாம்.\n\nPDF என்பதன் full form Portable Document Format.\n\nஒரு document எந்த computer அல்லது device-ல் திறந்தாலும் அதன் page layout மற்றும் appearance-ஐ முடிந்தவரை ஒரே மாதிரி வைத்திருக்க PDF பயன்படுத்தப்படுகிறது.\n\nஒரு PDF-ல் text, images, tables, headings மற்றும் page layout போன்றவை இருக்கும்.\n\nPDF-ன் முக்கியமான characteristic என்னவென்றால் இது page-oriented format. அதாவது document எப்படி ஒரு page-ல் தோன்றுகிறது என்பது மிகவும் முக்கியம்.",
      summaryNotes = "PDF stands for Portable Document Format. It maintains identical page layout across devices. Its primary characteristic is being page-oriented: fixed coordinates, fixed boundaries.",
      category = SceneCategory.PDF_FUNDAMENTALS,
      keyPoints = listOf(
        "Portable Document Format created for uniform printing & viewing",
        "Page-oriented: Elements tied to fixed X, Y page coordinates",
        "Contains text, images, tables, vectors, and font subsets",
        "Appearance is preserved strictly across all platforms"
      )
    ),
    TutorialScene(
      id = 3,
      titleEn = "PDF Problem for Reflowable Books",
      titleTa = "Reflowable புத்தகங்களில் PDF பிரச்சனை",
      durationSeconds = 55,
      durationDisplay = "0:55",
      onScreenHeading = "Fixed Layout vs Reflowable Content",
      onScreenSubheading = "PDF → Extract Content → Structure Content → Reflowable Book",
      voiceoverTamil = "ஆனால் இங்கே ஒரு முக்கியமான பிரச்சனை இருக்கிறது.\n\nPDF ஒரு fixed page layout-ஐ பயன்படுத்துகிறது.\n\nMobile screen-ல் அதே page-ஐ படிக்கும்போது text மிகவும் சிறியதாக இருக்கலாம்.\n\nஅதனால் reflowable digital book உருவாக்க PDF content-ஐ structure செய்ய வேண்டும்.",
      summaryNotes = "Fixed layouts fail on small screens because entire pages shrink, forcing users to pinch and zoom. Reflowable books adapt text size and wrap dynamically across screens of any size.",
      category = SceneCategory.PDF_FUNDAMENTALS,
      keyPoints = listOf(
        "Fixed page layout causes microscopic text on mobile screens",
        "Users are forced to pinch, zoom, and scroll horizontally",
        "Reflowable layout allows text to wrap fluidly across any screen width",
        "Solution: Extract content from PDF and structure it semantically"
      )
    ),
    TutorialScene(
      id = 4,
      titleEn = "What is HTML?",
      titleTa = "HTML என்றால் என்ன?",
      durationSeconds = 80,
      durationDisplay = "1:20",
      onScreenHeading = "HTML = Content + Structure",
      onScreenSubheading = "HyperText Markup Language",
      voiceoverTamil = "அடுத்ததாக HTML.\n\nHTML என்பதன் full form HyperText Markup Language.\n\nHTML ஒரு document-ல் எந்த content என்ன என்பதை structure செய்ய உதவுகிறது.\n\nஉதாரணமாக h1 ஒரு heading. p ஒரு paragraph. img ஒரு image. இப்படி HTML content-க்கு structure கொடுக்கிறது.",
      summaryNotes = "HTML stands for HyperText Markup Language. It provides semantic structure to content, labeling headers, paragraphs, lists, and images.",
      category = SceneCategory.WEB_FOUNDATIONS,
      keyPoints = listOf(
        "<h1> = Heading 1 (Chapter or Section Title)",
        "<p> = Paragraph text block",
        "<img> = Embedded image graphic",
        "<ul> & <ol> = Bulleted and numbered lists",
        "<table> = Tabular data structure"
      ),
      codeSnippet = "<h1>My Book</h1>\n\n<p>This is my first paragraph.</p>\n\n<p>This is my second paragraph.</p>",
      codeType = "html"
    ),
    TutorialScene(
      id = 5,
      titleEn = "What is CSS?",
      titleTa = "CSS என்றால் என்ன?",
      durationSeconds = 85,
      durationDisplay = "1:25",
      onScreenHeading = "HTML + CSS = Structured + Styled Content",
      onScreenSubheading = "Cascading Style Sheets",
      voiceoverTamil = "இப்போது CSS.\n\nCSS என்பதன் full form Cascading Style Sheets.\n\nHTML content என்ன என்பதை சொல்கிறது. CSS அந்த content எப்படி தோன்ற வேண்டும் என்பதை கட்டுப்படுத்துகிறது.\n\nHTML என்பது வீட்டின் structure என்றால் CSS என்பது அந்த வீட்டின் design மற்றும் decoration போன்றது.",
      summaryNotes = "CSS stands for Cascading Style Sheets. While HTML provides the skeleton and structure, CSS handles styling: fonts, sizes, colors, alignment, margins, and padding.",
      category = SceneCategory.WEB_FOUNDATIONS,
      keyPoints = listOf(
        "HTML = Structure (The house skeleton & walls)",
        "CSS = Presentation (Paint, interior design, lighting)",
        "Controls: Font, Size, Color, Alignment, Spacing, Margins",
        "Separation of concerns keeps content clean and maintainable"
      ),
      codeSnippet = "h1 {\n  font-size: 2em;\n  color: #1E3A8A;\n  text-align: center;\n  margin-bottom: 0.5em;\n}",
      codeType = "css"
    ),
    TutorialScene(
      id = 6,
      titleEn = "What is EPUB?",
      titleTa = "EPUB என்றால் என்ன?",
      durationSeconds = 75,
      durationDisplay = "1:15",
      onScreenHeading = "EPUB = Electronic Publication",
      onScreenSubheading = "Book → XHTML + CSS + Images + Metadata + Navigation → EPUB",
      voiceoverTamil = "இப்போது நமது முக்கியமான topic — EPUB.\n\nEPUB என்பதன் full form Electronic Publication.\n\nஇது digital books மற்றும் electronic publications உருவாக்கப் பயன்படுத்தப்படும் open publishing format.\n\nEPUB என்பது ஒரு single document மட்டும் அல்ல. பல files-ஐ ஒரு package-ஆக இணைத்து உருவாக்கப்படும் digital publication.",
      summaryNotes = "EPUB is the industry standard open format for digital books. Rather than a single flat file, it is an interconnected package of XHTML, CSS, images, metadata, and navigation.",
      category = SceneCategory.EPUB_CONCEPTS,
      keyPoints = listOf(
        "Electronic Publication - Open international e-book standard",
        "Reflowable text adapts to phones, tablets, and e-readers",
        "Composed of web technologies: XHTML, CSS, XML",
        "Packaged cleanly into a single distributable .epub file"
      )
    ),
    TutorialScene(
      id = 7,
      titleEn = "EPUB is a Package",
      titleTa = "EPUB ஒரு Package",
      durationSeconds = 60,
      durationDisplay = "1:00",
      onScreenHeading = "book.epub = ZIP Package Archive",
      onScreenSubheading = "mimetype + META-INF + EPUB",
      voiceoverTamil = "ஒரு EPUB file-ஐ நீங்கள் ஒரு package என்று நினைத்துக்கொள்ளலாம்.\n\nஅதற்குள் content, style, images, metadata மற்றும் navigation போன்ற பல files இருக்கும்.",
      summaryNotes = "An .epub file is actually a renamed ZIP archive containing standardized folders and files that e-readers uncompress and render seamlessly.",
      category = SceneCategory.EPUB_CONCEPTS,
      keyPoints = listOf(
        "Renaming .epub to .zip reveals its internal files",
        "Root level contains 'mimetype' file",
        "Contains 'META-INF' folder with container instructions",
        "Contains 'EPUB' (or OEBPS) content folder"
      ),
      codeSnippet = "book.epub\n│\n├── mimetype\n├── META-INF\n│   └── container.xml\n└── EPUB\n    ├── package.opf\n    └── ...",
      codeType = "tree"
    ),
    TutorialScene(
      id = 8,
      titleEn = "Complete EPUB Package Structure",
      titleTa = "முழுமையான EPUB Package கட்டமைப்பு",
      durationSeconds = 110,
      durationDisplay = "1:50",
      onScreenHeading = "MyBook.epub Complete Hierarchy",
      onScreenSubheading = "mimetype • META-INF • EPUB (OPF, Nav, XHTML, CSS, Images, Fonts)",
      voiceoverTamil = "இங்கே ஒரு complete EPUB package-ன் folder structure-ஐ பார்க்கலாம்.\n\nMyBook.epub-க்குள் mimetype, META-INF folder மற்றும் container.xml இருக்கும்.\n\nEPUB folder-க்குள் package.opf, nav.xhtml, css folder-ல் style.css, xhtml folder-ல் chapters, images folder மற்றும் fonts folder இருக்கும்.",
      summaryNotes = "A complete standard EPUB structure has three primary pillars: root mimetype, META-INF/container.xml pointing to OPF, and the content folder containing OPF, Nav, XHTML, CSS, images, and fonts.",
      category = SceneCategory.EPUB_PACKAGE,
      keyPoints = listOf(
        "mimetype: Uncompressed format specifier",
        "META-INF/container.xml: Bootstrap link to package.opf",
        "EPUB/package.opf: Master manifest, metadata, spine",
        "EPUB/nav.xhtml: Semantic EPUB3 table of contents",
        "xhtml/: Chapter text documents",
        "css/, images/, fonts/: Supporting assets"
      ),
      codeSnippet = "MyBook.epub\n│\n├── mimetype\n├── META-INF/\n│   └── container.xml\n└── EPUB/\n    ├── package.opf\n    ├── nav.xhtml\n    ├── css/style.css\n    ├── xhtml/\n    │   ├── chapter01.xhtml\n    │   ├── chapter02.xhtml\n    │   └── chapter03.xhtml\n    ├── images/\n    └── fonts/",
      codeType = "tree"
    ),
    TutorialScene(
      id = 9,
      titleEn = "The mimetype File",
      titleTa = "mimetype கோப்பு",
      durationSeconds = 50,
      durationDisplay = "0:50",
      onScreenHeading = "mimetype → EPUB Identification",
      onScreenSubheading = "Must be uncompressed at byte offset 38",
      voiceoverTamil = "முதலில் mimetype.\n\nஇந்த file இந்த package EPUB format என்பதை குறிப்பிடுகிறது.",
      summaryNotes = "The mimetype file must be the very first file in the ZIP archive, uncompressed (stored), with exact contents 'application/epub+zip' and no trailing newline.",
      category = SceneCategory.EPUB_PACKAGE,
      keyPoints = listOf(
        "Content: application/epub+zip",
        "Must NOT be compressed (stored with 0% compression)",
        "Must be the first file in the archive (byte 38 offset)",
        "Allows OS and e-readers to instantly detect valid EPUB"
      ),
      codeSnippet = "application/epub+zip",
      codeType = "xml"
    ),
    TutorialScene(
      id = 10,
      titleEn = "META-INF / container.xml",
      titleTa = "META-INF மற்றும் container.xml",
      durationSeconds = 60,
      durationDisplay = "1:00",
      onScreenHeading = "container.xml → Points to package.opf",
      onScreenSubheading = "The Bootstrap Pointer for EPUB Readers",
      voiceoverTamil = "அடுத்தது META-INF folder.\n\nஇதற்குள் container.xml இருக்கும்.\n\nஇந்த file முக்கியமான package document எங்கே இருக்கிறது என்பதை தெரிவிக்கிறது.",
      summaryNotes = "container.xml tells the reading system exactly where the master OPF file is stored inside the EPUB package via the full-path attribute.",
      category = SceneCategory.EPUB_PACKAGE,
      keyPoints = listOf(
        "Standardized bootstrap file across all EPUB versions",
        "Contains <rootfile full-path=\"EPUB/package.opf\" ... />",
        "Tells e-readers where to find the package publication rules",
        "Ensures reader doesn't have to guess file names"
      ),
      codeSnippet = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<container version=\"1.0\" xmlns=\"urn:oasis:names:tc:opendocument:xmlns:container\">\n  <rootfiles>\n    <rootfile full-path=\"EPUB/package.opf\"\n              media-type=\"application/oebps-package+xml\"/>\n  </rootfiles>\n</container>",
      codeType = "xml"
    ),
    TutorialScene(
      id = 11,
      titleEn = "XHTML Content Files",
      titleTa = "XHTML உள்ளடக்கக் கோப்புகள்",
      durationSeconds = 65,
      durationDisplay = "1:05",
      onScreenHeading = "XHTML = Book Content",
      onScreenSubheading = "Semantic XML-compliant Chapter Files",
      voiceoverTamil = "இங்கே actual book content இருக்கும்.\n\nChapter headings, paragraphs, lists மற்றும் பிற structured content XHTML files-ல் இருக்கும்.",
      summaryNotes = "EPUB content is written in strictly valid XHTML5. Every tag must be closed, and semantic tags (<section>, <header>, <p>) give meaning to assistive technologies.",
      category = SceneCategory.EPUB_PACKAGE,
      keyPoints = listOf(
        "XHTML is strict, XML-compliant HTML",
        "Contains actual chapter text: chapter01.xhtml, chapter02.xhtml",
        "Must be well-formed XML: all tags closed",
        "Semantic structure powers e-reader pagination and screen readers"
      ),
      codeSnippet = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<html xmlns=\"http://www.w3.org/1999/xhtml\">\n<head><title>Chapter 1</title></head>\n<body>\n  <section>\n    <h1>Chapter 1</h1>\n    <p>This is the first paragraph.</p>\n  </section>\n</body>\n</html>",
      codeType = "html"
    ),
    TutorialScene(
      id = 12,
      titleEn = "CSS Styling in EPUB",
      titleTa = "EPUB-ல் CSS ஸ்டைலிங்",
      durationSeconds = 60,
      durationDisplay = "1:00",
      onScreenHeading = "CSS = Presentation",
      onScreenSubheading = "Digital Typography & Reading Aesthetics",
      voiceoverTamil = "CSS folder-ல் book-ன் styling இருக்கும்.\n\nFont, size, spacing, alignment மற்றும் layout போன்ற presentation rules இங்கே இருக்கும்.",
      summaryNotes = "CSS defines styling for reading comfort: responsive margins, readable line heights, heading hierarchy, and night/day mode adaptation.",
      category = SceneCategory.EPUB_PACKAGE,
      keyPoints = listOf(
        "Defines typography: font-family, font-size, line-height",
        "Controls reading spacing: margins, paragraph indents",
        "Adapts to user light/dark theme settings",
        "Keeps styling separate from XHTML content"
      ),
      codeSnippet = "body {\n  font-family: serif;\n  line-height: 1.6;\n  margin: 5%;\n}\nh1 {\n  font-size: 1.8em;\n  color: #1E293B;\n  text-align: center;\n}",
      codeType = "css"
    ),
    TutorialScene(
      id = 13,
      titleEn = "Images & Media",
      titleTa = "படங்கள் மற்றும் ஊடகம்",
      durationSeconds = 55,
      durationDisplay = "0:55",
      onScreenHeading = "images/ → Visual Assets & Alt Text",
      onScreenSubheading = "Cover, Figures, Diagrams with Accessibility",
      voiceoverTamil = "Images folder-ல் cover, figures, charts மற்றும் பிற images இருக்கும்.",
      summaryNotes = "The images folder holds cover art and diagrams. In accessible EPUB3, every informational image in XHTML must have a descriptive alt attribute.",
      category = SceneCategory.EPUB_PACKAGE,
      keyPoints = listOf(
        "Stores cover.jpg, figures, charts, and diagrams",
        "Referenced in XHTML via <img src=\"../images/figure01.png\" alt=\"...\" />",
        "Alt text ensures blind readers understand the visual content",
        "Supported formats: JPEG, PNG, SVG, WebP"
      ),
      codeSnippet = "<figure>\n  <img src=\"../images/figure01.png\" \n       alt=\"Diagram of EPUB package structure\" />\n  <figcaption>Figure 1: Complete Package</figcaption>\n</figure>",
      codeType = "html"
    ),
    TutorialScene(
      id = 14,
      titleEn = "package.opf — The Heart of EPUB",
      titleTa = "package.opf — EPUB-ன் இதயம்",
      durationSeconds = 120,
      durationDisplay = "2:00",
      onScreenHeading = "package.opf = Metadata + Manifest + Spine",
      onScreenSubheading = "Spine = Reading Order",
      voiceoverTamil = "இப்போது EPUB package-ல் மிகவும் முக்கியமான file-களில் ஒன்றான package.opf-ஐ பார்க்கலாம்.\n\npackage.opf-ல் மூன்று முக்கியமான sections இருக்கும்:\n\n1. Metadata: Book title, author, language மற்றும் identifier போன்ற information.\n2. Manifest: EPUB package-ல் உள்ள files அனைத்தையும் பட்டியலிடுகிறது.\n3. Spine: Book content எந்த reading order-ல் படிக்க வேண்டும் என்பதை குறிப்பிடுகிறது.",
      summaryNotes = "The OPF (Open Packaging Format) file contains the metadata (who, what, language), manifest (exhaustive inventory of every file with media-type), and spine (linear sequential reading order).",
      category = SceneCategory.EPUB_PACKAGE,
      keyPoints = listOf(
        "<metadata>: Title, Author, Language, ISBN, Accessibility info",
        "<manifest>: Complete file list with unique IDs and media-types",
        "<spine>: Exact linear reading order (Chapter 1 → 2 → 3)",
        "Crucial: Any file in the package not in the manifest will fail validation!"
      ),
      codeSnippet = "<package version=\"3.0\" unique-identifier=\"book-id\">\n  <metadata>\n    <dc:title>Sample Book</dc:title>\n    <dc:language>ta</dc:language>\n  </metadata>\n  <manifest>\n    <item id=\"c1\" href=\"xhtml/ch01.xhtml\" media-type=\"application/xhtml+xml\"/>\n  </manifest>\n  <spine>\n    <itemref idref=\"c1\"/>\n  </spine>\n</package>",
      codeType = "xml"
    ),
    TutorialScene(
      id = 15,
      titleEn = "Navigation Document (nav.xhtml)",
      titleTa = "வழிசெலுத்தல் (nav.xhtml)",
      durationSeconds = 65,
      durationDisplay = "1:05",
      onScreenHeading = "nav.xhtml = Table of Contents",
      onScreenSubheading = "Interactive Navigation for E-Readers",
      voiceoverTamil = "nav.xhtml EPUB-ன் navigation மற்றும் Table of Contents-க்கு பயன்படுத்தப்படுகிறது.",
      summaryNotes = "EPUB3 uses nav.xhtml with the HTML5 <nav epub:type=\"toc\"> element to generate the interactive Table of Contents that allows readers to tap and jump to any chapter.",
      category = SceneCategory.EPUB_PACKAGE,
      keyPoints = listOf(
        "Replaces the older EPUB2 toc.ncx with clean HTML5",
        "Uses <nav epub:type=\"toc\"> with standard ordered lists <ol><li>",
        "Clicking any entry jumps directly to chapter location",
        "Required for EPUB3 compliance and accessible navigation"
      ),
      codeSnippet = "<nav epub:type=\"toc\" id=\"toc\">\n  <h1>Table of Contents</h1>\n  <ol>\n    <li><a href=\"xhtml/ch01.xhtml\">Chapter 1: Intro</a></li>\n    <li><a href=\"xhtml/ch02.xhtml\">Chapter 2: HTML & CSS</a></li>\n    <li><a href=\"xhtml/ch03.xhtml\">Chapter 3: EPUB Packaging</a></li>\n  </ol>\n</nav>",
      codeType = "html"
    ),
    TutorialScene(
      id = 16,
      titleEn = "EPUB Complete Package Summary",
      titleTa = "EPUB Complete Package சுருக்கம்",
      durationSeconds = 70,
      durationDisplay = "1:10",
      onScreenHeading = "The Complete Harmony of EPUB Components",
      onScreenSubheading = "mimetype → container.xml → OPF → Nav → XHTML + CSS",
      voiceoverTamil = "இப்போது ஒரு EPUB package-ன் முக்கியமான components அனைத்தையும் பார்த்துவிட்டோம்.",
      summaryNotes = "Everything fits together like clockwork: mimetype identifies the archive, container.xml locates package.opf, package.opf orchestrates files and reading order, nav.xhtml guides the user, and XHTML/CSS deliver the reading experience.",
      category = SceneCategory.EPUB_PACKAGE,
      keyPoints = listOf(
        "1. mimetype validates format",
        "2. container.xml points to package.opf",
        "3. package.opf registers all files & reading order",
        "4. nav.xhtml provides table of contents",
        "5. xhtml + css + images provide the rich book content"
      )
    ),
    TutorialScene(
      id = 17,
      titleEn = "PDF to EPUB Complete Workflow",
      titleTa = "PDF முதல் EPUB வரை முழுமையான Workflow",
      durationSeconds = 120,
      durationDisplay = "2:00",
      onScreenHeading = "PDF to EPUB3 End-to-End Pipeline",
      onScreenSubheading = "Analysis → Extraction → Structure → EPUB3 → Validation",
      voiceoverTamil = "ஒரு PDF-ஐ EPUB ஆக மாற்றும்போது PDF-ஐ அப்படியே EPUB-க்குள் போடுவதில்லை.\n\nமுதலில் PDF-ஐ analyse செய்ய வேண்டும். Text மற்றும் images-ஐ identify செய்ய வேண்டும். Reading order-ஐ கண்டறிய வேண்டும்.\n\nHeading, paragraph, list, table, figure போன்ற structures-ஐ கண்டறிய வேண்டும்.\n\nபிறகு structured content-ஐ XHTML ஆக உருவாக்க வேண்டும். CSS மூலம் styling கொடுக்க வேண்டும். Metadata மற்றும் navigation சேர்க்க வேண்டும். அனைத்து files-ஐ EPUB package-ஆக உருவாக்க வேண்டும். இறுதியில் EPUB-ஐ validate செய்ய வேண்டும்.",
      summaryNotes = "Never simply embed or dump raw PDF pages into EPUB. Follow the professional 17-step pipeline: analyze layout, extract content, determine reading order, detect semantic elements, construct XHTML/CSS, generate metadata and navigation, build package, and validate with epubcheck.",
      category = SceneCategory.WORKFLOW_ACCESSIBILITY,
      keyPoints = listOf(
        "Phase 1: PDF Layout Analysis & Reading Order Detection",
        "Phase 2: Semantic Element Classification (Headings, Paragraphs, Tables, Figures)",
        "Phase 3: Clean XHTML Generation & CSS Styling",
        "Phase 4: Navigation (nav.xhtml) & OPF Packaging (Metadata, Manifest, Spine)",
        "Phase 5: Automated EPUBCheck Validation & Accessibility Testing"
      )
    ),
    TutorialScene(
      id = 18,
      titleEn = "Accessible EPUB3",
      titleTa = "Accessible EPUB3 — அணுகக்கூடிய மின்புத்தகம்",
      durationSeconds = 80,
      durationDisplay = "1:20",
      onScreenHeading = "Accessible EPUB3 Standards",
      onScreenSubheading = "Inclusive Digital Publishing for All Learners",
      voiceoverTamil = "நாம் உருவாக்கும் EPUB ஒரு simple ebook மட்டும் அல்ல.\n\nAccessible EPUB3 உருவாக்கும்போது semantic structure, meaningful reading order, image alternative text, navigation மற்றும் accessibility metadata போன்றவற்றையும் கவனிக்க வேண்டும்.",
      summaryNotes = "Accessible EPUB3 ensures books can be read by everyone, including visually impaired and print-disabled readers using screen readers, braille displays, and text-to-speech.",
      category = SceneCategory.WORKFLOW_ACCESSIBILITY,
      keyPoints = listOf(
        "Semantic HTML: <main>, <section>, <header>, <h1>-<h6> hierarchy",
        "Image Alt Text: Descriptive descriptions for all informative images",
        "Logical Reading Order: Ensuring multi-column PDF text flows correctly",
        "Language Tagging: xml:lang=\"ta\" for accurate Tamil text-to-speech",
        "Accessibility Metadata: Schema.org accessMode, accessibilityFeature tags"
      )
    ),
    TutorialScene(
      id = 19,
      titleEn = "Final Recap",
      titleTa = "பாடச் சுருக்கம் (Final Recap)",
      durationSeconds = 85,
      durationDisplay = "1:25",
      onScreenHeading = "Key Takeaways Summary",
      onScreenSubheading = "7 Core Concepts of Digital Publishing",
      voiceoverTamil = "சுருக்கமாக சொன்னால்:\n\nPDF என்பது page-oriented document.\n\nHTML அல்லது XHTML content மற்றும் structure-ஐ உருவாக்குகிறது.\n\nCSS presentation-ஐ control செய்கிறது.\n\nEPUB பல files-ஐ இணைத்த digital publication package.\n\nOPF package information, resources மற்றும் reading order-ஐ manage செய்கிறது.\n\nNAV navigation மற்றும் Table of Contents-ஐ வழங்குகிறது.\n\nஇவை அனைத்தும் சேர்ந்து ஒரு EPUB3 publication உருவாகிறது.",
      summaryNotes = "Review the 7 pillars: PDF is fixed page-oriented; HTML is content & structure; CSS is presentation; EPUB is a publication package; OPF coordinates resources and reading order; NAV powers TOC; EPUB3 delivers accessible reflowable books.",
      category = SceneCategory.RECAP_QUIZ,
      keyPoints = listOf(
        "1. PDF: Page-oriented document with fixed layout",
        "2. HTML/XHTML: Content + Semantic Structure",
        "3. CSS: Presentation and Styling",
        "4. EPUB: Digital Publication Package (ZIP archive)",
        "5. OPF: Package info + Manifest + Spine reading order",
        "6. NAV: Interactive Table of Contents",
        "7. EPUB3: Accessible, reflowable modern publication"
      )
    ),
    TutorialScene(
      id = 20,
      titleEn = "Learning Challenge (Interactive Quiz)",
      titleTa = "கற்றல் சவால் (வினாடி-வினா)",
      durationSeconds = 100,
      durationDisplay = "1:40",
      onScreenHeading = "Interactive Knowledge Check",
      onScreenSubheading = "Test Your Understanding of PDF, HTML, CSS & EPUB",
      voiceoverTamil = "இப்போது நாம் கற்றுக்கொண்டதை ஒரு சிறிய வினாடி-வினா மூலம் சரிபார்க்கலாம்!\n\nகேள்விகளுக்கு சரியான பதிலை தேர்ந்தெடுங்கள்.",
      summaryNotes = "Engage in an interactive quiz checking CSS purpose, EPUB chapter formats, and the spine component.",
      category = SceneCategory.RECAP_QUIZ,
      keyPoints = listOf(
        "Q1: CSS controls presentation and styling",
        "Q2: Book content inside EPUB is formatted in XHTML",
        "Q3: The <spine> element in package.opf specifies reading order"
      )
    ),
    TutorialScene(
      id = 21,
      titleEn = "Final Message & Next Steps",
      titleTa = "நிறைவுச் செய்தி மற்றும் அடுத்த பாடம்",
      durationSeconds = 60,
      durationDisplay = "1:00",
      onScreenHeading = "Next Lesson → PDF Structure & Reading Order",
      onScreenSubheading = "Congratulations on Completing the Foundations!",
      voiceoverTamil = "இந்த lesson முடிந்ததும் PDF, HTML, CSS மற்றும் EPUB ஆகியவற்றுக்கிடையிலான தொடர்பு உங்களுக்கு தெளிவாக இருக்க வேண்டும்.\n\nஅடுத்த lessons-ல் நாம் ஒவ்வொரு component-ஐ practical-ஆக உருவாக்கிப் பார்க்கப் போகிறோம்.\n\nஇறுதியில் ஒரு complete PDF to Accessible EPUB3 workflow-ஐ உருவாக்குவோம்.",
      summaryNotes = "Now you have a strong mental model of how PDF, HTML, CSS, and EPUB interconnect. In upcoming lessons, we will build each component hands-on and master PDF to Accessible EPUB3 conversion.",
      category = SceneCategory.RECAP_QUIZ,
      keyPoints = listOf(
        "Solid conceptual grasp of the publishing tech stack",
        "Next Lesson: Deep dive into PDF Structure & Reading Order extraction",
        "Hands-on practice: Building package.opf and nav.xhtml from scratch",
        "Goal: Master PDF to Accessible EPUB3 production"
      )
    )
  )

  val quizQuestions: List<QuizQuestion> = listOf(
    QuizQuestion(
      id = 1,
      questionTa = "CSS-ன் முக்கியமான வேலை என்ன?",
      questionEn = "What is the primary function of CSS?",
      options = listOf(
        "A. PDF உருவாக்குவது",
        "B. Content-ன் presentation மற்றும் styling-ஐ control செய்வது",
        "C. EPUB delete செய்வது",
        "D. Image scan செய்வது"
      ),
      correctIndex = 1, // B
      explanationTa = "சரியான விடை: B. HTML உள்ளடக்கத்தின் structure-ஐ தீர்மானித்தால், CSS அதன் நிறம், எழுத்துரு அளவு, layout போன்ற presentation மற்றும் styling-ஐ கட்டுப்படுத்துகிறது.",
      explanationEn = "Correct Answer: B. While HTML provides the structure of content, CSS controls its presentation, styling, typography, colors, and layout."
    ),
    QuizQuestion(
      id = 2,
      questionTa = "EPUB package-ல் book content பொதுவாக எந்த format-ல் இருக்கும்?",
      questionEn = "In which format is book content typically stored inside an EPUB package?",
      options = listOf(
        "A. XHTML",
        "B. EXE",
        "C. MP3",
        "D. TXT only"
      ),
      correctIndex = 0, // A
      explanationTa = "சரியான விடை: A. EPUB3-ல் அனைத்து அத்தியாயங்களும் (Chapters) மற்றும் பக்கங்களும் XML விதிகளுக்கு உட்பட்ட semantic XHTML வடிவத்தில் சேமிக்கப்படுகின்றன.",
      explanationEn = "Correct Answer: A. In EPUB3, all book chapters and textual content are stored as well-formed semantic XHTML documents."
    ),
    QuizQuestion(
      id = 3,
      questionTa = "EPUB-ல் reading order-ஐ எந்த component குறிப்பிடுகிறது?",
      questionEn = "Which component in an EPUB package defines the sequential reading order?",
      options = listOf(
        "A. CSS",
        "B. Image",
        "C. Spine",
        "D. Font"
      ),
      correctIndex = 2, // C
      explanationTa = "சரியான விடை: C. package.opf கோப்பில் உள்ள <spine> பகுதி தான், வாசகர் புத்தகத்தை அடுத்தடுத்து எந்த வரிசையில் (Chapter 1 → Chapter 2) படிக்க வேண்டும் என்பதை குறிப்பிடுகிறது.",
      explanationEn = "Correct Answer: C. The <spine> section inside package.opf determines the precise linear reading order of documents for the reading system."
    )
  )

  val sampleEpubFiles: List<EpubFileItem> = listOf(
    EpubFileItem(
      path = "mimetype",
      name = "mimetype",
      extension = "MIME",
      purposeTa = "இந்த package ஒரு EPUB கோப்பு என்பதை e-reader மற்றும் OS-க்கு தெரிவிக்கிறது.",
      purposeEn = "Identifies the ZIP container as an EPUB file. Must be uncompressed at byte 38.",
      codeSnippet = "application/epub+zip",
      level = 0
    ),
    EpubFileItem(
      path = "META-INF/container.xml",
      name = "container.xml",
      extension = "XML",
      purposeTa = "முக்கிய package.opf ஆவணம் எங்கே உள்ளது என்பதை சுட்டிக்காட்டுகிறது.",
      purposeEn = "Bootstrap file pointing the reading system to package.opf.",
      codeSnippet = """<?xml version="1.0" encoding="UTF-8"?>
<container version="1.0" xmlns="urn:oasis:names:tc:opendocument:xmlns:container">
  <rootfiles>
    <rootfile full-path="EPUB/package.opf"
              media-type="application/oebps-package+xml"/>
  </rootfiles>
</container>""",
      level = 1
    ),
    EpubFileItem(
      path = "EPUB/package.opf",
      name = "package.opf",
      extension = "OPF",
      purposeTa = "Metadata (தலைப்பு, ஆசிரியர்), Manifest (அனைத்து கோப்புகள் பட்டியல்), Spine (படிக்கும் வரிசை).",
      purposeEn = "Master publication document holding metadata, manifest, and spine reading order.",
      codeSnippet = """<?xml version="1.0" encoding="UTF-8"?>
<package xmlns="http://www.idpf.org/2007/opf" version="3.0" unique-identifier="pub-id">
  <metadata xmlns:dc="http://purl.org/dc/elements/1.1/">
    <dc:identifier id="pub-id">urn:uuid:978-0-123456-47-2</dc:identifier>
    <dc:title>டிஜிட்டல் பப்ளிஷிங் அடிப்படைகள்</dc:title>
    <dc:language>ta</dc:language>
    <dc:creator>Tamil Publishing Lab</dc:creator>
    <meta property="dcterms:modified">2026-09-18T00:00:00Z</meta>
  </metadata>
  <manifest>
    <item id="nav" href="nav.xhtml" media-type="application/xhtml+xml" properties="nav"/>
    <item id="style" href="css/style.css" media-type="text/css"/>
    <item id="ch01" href="xhtml/chapter01.xhtml" media-type="application/xhtml+xml"/>
    <item id="ch02" href="xhtml/chapter02.xhtml" media-type="application/xhtml+xml"/>
    <item id="cover-img" href="images/cover.jpg" media-type="image/jpeg" properties="cover-image"/>
  </manifest>
  <spine>
    <itemref idref="ch01"/>
    <itemref idref="ch02"/>
  </spine>
</package>""",
      level = 1
    ),
    EpubFileItem(
      path = "EPUB/nav.xhtml",
      name = "nav.xhtml",
      extension = "XHTML",
      purposeTa = "புத்தகத்தின் பொருளடக்கம் (Table of Contents). வாசகர் அத்தியாயங்களுக்கு நேரடியாக செல்ல உதவுகிறது.",
      purposeEn = "Navigation document containing the accessible Table of Contents and landmark links.",
      codeSnippet = """<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:epub="http://www.idpf.org/2007/ops" xml:lang="ta">
<head>
  <title>பொருளடக்கம்</title>
  <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>
  <nav epub:type="toc" id="toc">
    <h1>பொருளடக்கம் (Contents)</h1>
    <ol>
      <li><a href="xhtml/chapter01.xhtml">பாடம் 1: PDF மற்றும் அதன் வரம்புகள்</a></li>
      <li><a href="xhtml/chapter02.xhtml">பாடம் 2: HTML மற்றும் CSS கட்டமைப்பு</a></li>
      <li><a href="xhtml/chapter03.xhtml">பாடம் 3: முழுமையான EPUB தொகுப்பு</a></li>
    </ol>
  </nav>
</body>
</html>""",
      level = 1
    ),
    EpubFileItem(
      path = "EPUB/css/style.css",
      name = "style.css",
      extension = "CSS",
      purposeTa = "எழுத்துரு வகை, அளவு, இடைவெளி, வண்ணங்கள் மற்றும் லேஅவுட் ஸ்டைலிங்.",
      purposeEn = "Cascading Style Sheets defining reading typography, spacing, and layout aesthetics.",
      codeSnippet = """/* EPUB3 Digital Publishing Stylesheet */
body {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif;
  line-height: 1.6;
  margin: 4%;
  color: #1E293B;
}

h1 {
  font-size: 1.8em;
  color: #1E3A8A;
  text-align: center;
  margin-top: 1.5em;
  margin-bottom: 0.8em;
}

p {
  text-indent: 1.5em;
  margin: 0.5em 0;
  text-align: justify;
}

figure {
  margin: 1.5em 0;
  text-align: center;
}

img {
  max-width: 100%;
  height: auto;
}""",
      level = 2
    ),
    EpubFileItem(
      path = "EPUB/xhtml/chapter01.xhtml",
      name = "chapter01.xhtml",
      extension = "XHTML",
      purposeTa = "புத்தகத்தின் முதல் அத்தியாயத்தின் உரை உள்ளடக்கம் (Content + Structure).",
      purposeEn = "First chapter text content marked up with semantic HTML5 elements.",
      codeSnippet = """<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ta">
<head>
  <title>பாடம் 1: PDF என்றால் என்ன?</title>
  <link rel="stylesheet" type="text/css" href="../css/style.css"/>
</head>
<body>
  <section epub:type="chapter" role="doc-chapter">
    <h1>பாடம் 1: PDF மற்றும் அதன் வரம்புகள்</h1>
    <p>PDF என்பதன் முழு வடிவம் Portable Document Format ஆகும். இது ஆவணங்களை அச்சு வடிவத்திற்கு தகுந்தவாறு ஒரே மாதிரி காண்பிக்க வடிவமைக்கப்பட்டது.</p>
    <p>ஆனால் மொபைல் திரைகளில் வாசிக்கும் போது, இந்த நிலையான வடிவம் சிறியதாக மாறுவதால் reflowable EPUB வடிவத்திற்கு மாற்ற வேண்டியது அவசியமாகிறது.</p>
  </section>
</body>
</html>""",
      level = 2
    )
  )

  val glossaryItems: List<GlossaryItem> = listOf(
    GlossaryItem(
      term = "PDF",
      fullForm = "Portable Document Format",
      definitionTa = "பக்க வடிவிலான (Page-oriented), அச்சுக்கு உகந்த, நிலையான layout கொண்ட ஆவண வடிவம்.",
      definitionEn = "A fixed-layout document standard designed for visual fidelity across printing and desktop viewing.",
      iconType = "PDF"
    ),
    GlossaryItem(
      term = "HTML",
      fullForm = "HyperText Markup Language",
      definitionTa = "ஆவணத்தின் உள்ளடக்கத்திற்கு (தலைப்பு, பத்தி, படம்) அர்த்தமுள்ள கட்டமைப்பை வழங்கும் மொழி.",
      definitionEn = "The standard markup language providing semantic structure to digital documents and web pages.",
      iconType = "HTML"
    ),
    GlossaryItem(
      term = "CSS",
      fullForm = "Cascading Style Sheets",
      definitionTa = "HTML உள்ளடக்கத்தின் வடிவம், நிறம், எழுத்துரு மற்றும் லேஅவுட்டை அழகாக்கும் ஸ்டைலிங் மொழி.",
      definitionEn = "Style sheet language used to describe the presentation, formatting, and layout of structured documents.",
      iconType = "CSS"
    ),
    GlossaryItem(
      term = "EPUB",
      fullForm = "Electronic Publication",
      definitionTa = "டிஜிட்டல் புத்தகங்களுக்கான சர்வதேச திறந்தநிலை reflowable மின்புத்தக வடிவம்.",
      definitionEn = "The worldwide standard reflowable format for digital publications and electronic books.",
      iconType = "EPUB"
    ),
    GlossaryItem(
      term = "OPF",
      fullForm = "Open Packaging Format (package.opf)",
      definitionTa = "EPUB-ன் முதன்மை கட்டுப்பாட்டு கோப்பு. Metadata, Manifest மற்றும் Spine-ஐ நிர்வகிக்கிறது.",
      definitionEn = "The core XML file holding the package metadata, manifest inventory, and sequential reading spine.",
      iconType = "OPF"
    ),
    GlossaryItem(
      term = "Spine",
      fullForm = "Linear Reading Order (Spine)",
      definitionTa = "வாசகர் புத்தகத்தை எந்த வரிசையில் வாசிக்க வேண்டும் என்பதை நிர்ணயிக்கும் முதுகெலும்பு போன்ற பகுதி.",
      definitionEn = "The spine element inside package.opf that specifies the linear order of reading documents.",
      iconType = "OPF"
    ),
    GlossaryItem(
      term = "Manifest",
      fullForm = "Package Manifest Inventory",
      definitionTa = "EPUB தொகுப்பில் உள்ள ஒவ்வொரு கோப்பையும் அதன் தனித்துவ ID மற்றும் media-type உடன் பட்டியலிடும் பகுதி.",
      definitionEn = "The complete registry listing every file inside the EPUB with its unique identifier and media type.",
      iconType = "OPF"
    ),
    GlossaryItem(
      term = "Navigation",
      fullForm = "Navigation Document (nav.xhtml)",
      definitionTa = "பொருளடக்கம் (Table of Contents) மற்றும் அத்தியாய இணைப்புகளை வழங்கும் EPUB3 ஆவணம்.",
      definitionEn = "The HTML5-based navigation document delivering the table of contents and landmarks.",
      iconType = "NAV"
    ),
    GlossaryItem(
      term = "mimetype",
      fullForm = "MIME Type File",
      definitionTa = "தொகுப்பு EPUB தான் என்பதை உறுதிப்படுத்தும் சுருக்கப்படாத (uncompressed) அடையாள கோப்பு.",
      definitionEn = "The first file in the ZIP archive indicating 'application/epub+zip' with no compression.",
      iconType = "MIME"
    ),
    GlossaryItem(
      term = "container.xml",
      fullForm = "META-INF/container.xml",
      definitionTa = "e-reader செயலிக்கு package.opf ஆவணம் எங்குள்ளது என்பதைக் காட்டும் வழிகாட்டி கோப்பு.",
      definitionEn = "The root XML descriptor inside META-INF pointing to the exact location of package.opf.",
      iconType = "XML"
    ),
    GlossaryItem(
      term = "Reflowable",
      fullForm = "Reflowable Layout",
      definitionTa = "திரையின் அகலத்திற்கு ஏற்ப உரை தானாக மடங்கி, வசதியாக வாசிக்கக்கூடிய இயல்பு.",
      definitionEn = "Content layout that automatically wraps and adjusts typography according to the reader's screen width.",
      iconType = "EPUB"
    ),
    GlossaryItem(
      term = "Accessible EPUB3",
      fullForm = "Accessibility Standards (WCAG & EPUB 1.1)",
      definitionTa = "பார்வைக்குறைபாடு உள்ளவர்களும் screen reader மூலம் வாசிக்கும் வகையில் உருவாக்கப்பட்ட மின்புத்தகம்.",
      definitionEn = "Digital publication authored with semantic HTML, alt text, and metadata for inclusive access.",
      iconType = "ACCESSIBLE"
    )
  )

  val workflowSteps: List<String> = listOf(
    "1. PDF Layout Analysis",
    "2. Text & Image Extraction",
    "3. Reading Order Detection",
    "4. Heading & Hierarchy Classification",
    "5. Paragraph & List Detection",
    "6. Table & Figure Structuring",
    "7. Semantic XHTML Generation",
    "8. CSS Styling Rules",
    "9. Image Alt-Text & Accessibility",
    "10. Metadata Authoring (Title, Author, Lang)",
    "11. Navigation Document (nav.xhtml)",
    "12. Master OPF Package Creation",
    "13. EPUB3 Packaging (ZIP with mimetype)",
    "14. EPUBCheck Validation"
  )
}
