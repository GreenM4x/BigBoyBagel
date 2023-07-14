````mermaid
classDiagram
    namespace Datenbank{
        class Artikel{
            -artNr: int
            -artName: String
            -artPreis: double
            -bildSrc: String
            +Artikel(artNr: int, artName: String, artPreis, bildSrc: String)
            +getArtNr(): int
            +getArtName(): String
            +getArtPreis(): double
            +getBildSrc(): String
        }
        class Daten{
            +final dbTreiber: String$
            +final host: String$
            +final port: String$
            +final db: String $ 
            +final user: String $
            +final passwd: String $
        }
    
        class DBTabelleLesen{
            +baueVerbindungAuf(): connection$
            +DB_getArtikelByNr(ArtNr: int)$
        }
    }
    namespace Logic {
        class DiscountCode{
            <<enumeration>>
            -final vallue: double
            WELLI10(double: 0.1)
            BBB50(double: 0.5)
            FREEFROALL(double: 1)
            +DiscountCode(vallue: double)
            +getVallue() : double
        }
        class GetDBVallues {
            +getDBArtName(ArtNr: int): String$
            +getDBArtPreisString(ArtNr: int): String$
            +getDBArtPreisDouble(ArtNr: int): double$
            +GetDBArtBild(ArtNr: int): String$
        }
        class OrderCals{
            +OrderNe: int$
            +discountVallue: String$ 
            +counters: int[]$
            +AddToCounter(ArtNr: int): void$
            +RemoveFromCounter(ArtNr: int): void$
            +GetCounterValue(): int$
            +CheckIfCodeExist(code: String): double$
            +Order(): void$
            
            
        }
        class RechnungSchreiben{
            +path: String$
            +NeueRechnungSchreiben(rechnungsNr: int ,totalPreis: double,wahrencorp: int[],discound: double): void
        }
        class RechnugsLog{
            +path: String$
            +writeLog(rechnungsNr: int,totalPreis: double): void
        }
    }
    DBTabelleLesen <|-- Daten : implements
    DBTabelleLesen .. Artikel
    DBTabelleLesen .. GetDBVallues
    OrderCals .. GetDBVallues
    OrderCals .. DiscountCode
    OrderCals .. RechnungSchreiben
    OrderCals .. RechnugsLog

````