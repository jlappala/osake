program osake; {versio 1.55 27/08/95} 
uses crt,graph,win;

var summa        :longint;
    summa2       :longint;
    i            :byte; {for-lauseisiin}
    numero       :integer;
    y            :integer;
    mnro         :integer;
    mkpl         :longint;
    kpl          :longint;
    x            :byte;
    code         :integer;
    km           :char;
    pvalinta     :char;
    kysy         :char;
    valinta      :char;
    merkki       :char;
    lk           :char;
    kysymys      :char;
    kpy          :byte; {talletuskorko}
    ptuki        :byte;
    varma        :char;
    ok           :char;
    yhtionimi    :array [1..12] of string;
    nimi         :array [1..3]  of string;
    varat        :array [1..3]  of longint;
    saldo        :array [1..3]  of longint;
    osakemaara   :array [1..3,1..12] of longint;
    muutos       :array [1..12] of shortint;
    arvo         :array [1..12] of integer;
    maxosake     :array [1..12] of longint;
    ompros       :array [1..3,1..12] of real;
    markkina     :array [1..12] of longint;
    plm          :integer; {pelaajien lukum„„r„}
    kierros      :integer;
    onkokoko     :boolean;
    
procedure testplm;
var k5:string;
begin
 if (plm<=0) or (plm>3) then begin
  gotoxy(10,12); writeln('1-3 !!!!!!'); delay(1000); for i:=11 to 12 do begin
  gotoxy(10,i); writeln('            '); end;
  repeat gotoxy(10,11); readln(k5); val(k5,plm,code); until code=0;
  testplm;
 end;
end;
        
procedure lukumaara;
var k4:string;
begin
 textbackground(black); textcolor(lightgray);
 clrscr; repeat gotoxy(10,10); writeln('Kuinka monta pelaajaa (1-3) ?');
 gotoxy(10,11); readln(k4); val(k4,plm,code); until code=0; testplm;
end;

procedure aseta;
begin
 varma:='e';
 kierros:=1;
 kpy:=2;
 for i:=1 to 12 do begin
  arvo[i]:=100;
  muutos[i]:=0;
  maxosake[i]:=50000;
  markkina[i]:=50000;
 end;
 for i:=1 to plm do begin
  varat[i]:=5000;
  saldo[i]:=0;
  end;
 for x:=1 to plm do begin for i:=1 to 12 do begin
 osakemaara[x,i]:=0; ompros[x,i]:=0;
 end; end; 
end;

procedure alkuvalikko;
begin
 textbackground(black); clrscr;
 window(3,3,78,9); FrameWin('',doubleframe,1,15);
 clrscr;
 textcolor(lightblue);
 gotoxy(3,2);
 writeln('                               ß  ß ß  ß           ß  ß      ß  ß');
 gotoxy(3,3);
 writeln('ÛÛßÛ ÛÛßß ÛÛßÛ Û Û  ÛÛßß  ÛÛßß ÛÛßÛ ÛÛßÛ ÛÛßß ßÛÛß ÛÛßÛ    Û ÛÛßÛ ßÛÛß');
 gotoxy(3,4);
 writeln('ÛÛ Û ßßßÛ ÛÛßÛ ÛÛßÛ ÛÛßß  ßßßÛ ÛÛßÛ ÛÛßÛ ßßßÛ  ÛÛ  ÛÛßÛ Ü  Û ÛÛßÛ  ÛÛ ');
 gotoxy(3,5);
 writeln('ÛÛÜÛ ÛÜÜÛ ÛÛ Û ÛÛ Û ÛÛÜÜÜ ÛÜÜÛ ÛÛ Û ÛÛ Û ÛÜÜÛ  ÛÛ  ÛÛ Û ÛÜÜÛ ÛÛ Û  ÛÛ ');
 window(27,12,53,21); framewin(' P„„valikko ',singleframe,13,15);
 textcolor(lightblue); gotoxy(5,2); write('A'); textcolor(lightcyan);
 writeln('loita uusi peli');
 gotoxy(5,3); writeln('Tallenna peli');
 gotoxy(5,4); writeln('Lataa peli');
 gotoxy(5,5); write('A'); textcolor(lightblue); write('s');
 textcolor(lightcyan); writeln('etukset');
 textcolor(lightblue); gotoxy(5,7); write('P'); textcolor(lightcyan);
 writeln('aluu DOSiin'); gotoxy(5,8);
 merkki:=readkey;
 window(1,1,80,25);
end;

procedure testyhtio;
begin
 if length(yhtionimi[i])>10 then begin
  writeln('Yhti”n nimi korkeintaan kymmenen merkki„ !!');
  writeln('Yhti” ',i,' ?');
  readln(yhtionimi[i]); testyhtio; end;
end;

procedure lue_yhtiot;
begin
 for i:=1 to 12 do begin
  writeln('Yhti” ',i,' ?');
  readln(yhtionimi[i]);
  testyhtio;
 end; 
end;

procedure testnimi;
begin
 if length(nimi[i])>6 then begin
  writeln('Nimen pituus korkeintaan 6 merkki„ !!');
  writeln('Pelaajan ',i,' nimi ?'); readln(nimi[i]); testnimi; end;
end;

procedure lue_nimet;
begin
 clrscr;
 writeln; writeln;
 for i:=1 to plm do begin
  writeln('Pelaajan ',i,' nimi ?'); readln(nimi[i]);
  testnimi;
 end;
end;

procedure tyhja;
begin
end;

procedure laskeompros;
var y,z:byte;
begin
 for y:=1 to plm do for z:=1 to 12 do 
 ompros[y,z]:=(osakemaara[y,z]/maxosake[z])*100;
end;
 
procedure testnro;
var k6:string;
begin
 if (numero>12) or (numero<=0) then  begin
  gotoxy(6,22);
  writeln('Anteeksi v„„r„ numero !!');
  delay(1000); gotoxy (6,22); writeln('                        ');
  repeat gotoxy(6,21); writeln('N:o ? ');
  gotoxy(13,21); readln(k6); val(k6,numero,code); until code=0;
  testnro; end
end;

procedure testkpl;
var roska :longint;
    k7    :string;
begin
 roska:=kpl*arvo[numero];
 if (kpl<0) or (roska>varat[x]) then begin gotoxy (6,23);
  writeln('Budjetti petti, per....!');
  delay(1000); for i:=22 to 23 do begin gotoxy(6,i);
  writeln('                        '); end;
  repeat gotoxy(6,22); Writeln('kpl ? ');
  gotoxy (13,22); readln(k7); val(k7,kpl,code); until code=0;
  testkpl; end
end;

procedure testmarkkina1;
var k8:string;
begin
 if kpl>markkina[numero] then begin gotoxy (6,23);
  writeln('Tyhj„st„ on paha nyhj„st„...');
  delay(1000); for i:=22 to 23 do begin gotoxy(6,i);
  writeln('                            '); end;
  repeat gotoxy(6,22);
  Writeln('kpl ? '); gotoxy (13,22); readln(k8);
  val(k8,kpl,code); until code=0; testkpl; end
end;

procedure testmarkkina2;
begin
 if (varat[x] div arvo[numero])>markkina[numero] then begin
  varat[x]:=varat[x]-(markkina[numero]*arvo[numero]);
  osakemaara[x,numero]:=osakemaara[x,numero]+markkina[numero];
  markkina[numero]:=0; onkokoko:=true; end
end;

procedure tyhjenna;
var xyz:byte;
    zxc:byte;
begin
 for zxc:=1 to 80 do
 for xyz:=20 to 24 do begin gotoxy(zxc,xyz);
  write(chr(32));
   end
end;

procedure testok;
begin
 if (ok<>'O') and (ok<>'o') and (ok<>'K') and (ok<>'k')
 then begin
  gotoxy(6,21); writeln('           ');
  gotoxy(6,21); ok:=readkey;
  testok; end
end;

procedure osto;
var k1:string;
begin
 textcolor(lightred);
 gotoxy(6,20); write('O'); textcolor(white); write('sta / ');
 textcolor(lightred); write('K'); textcolor(white); writeln('oko rahalla');
 gotoxy(6,21); ok:=readkey; testok;
 case ok of
  'K','k': begin onkokoko:=false;
           repeat gotoxy(6,21); write('N:o ?'); gotoxy(13,21);
           readln(k1); val (k1,numero,code); until code=0;
           testnro; testmarkkina2;
            if onkokoko=false then begin
            markkina[numero]:=markkina[numero]-(varat[x] div arvo[numero]);
            osakemaara[x,numero]:=osakemaara[x,numero]+(varat[x] div
            arvo[numero]); varat[x]:=varat[x]-((varat[x] div arvo[numero])
            *arvo[numero]); end;
            tyhjenna; laskeompros; end;
  'O','o': begin
           repeat gotoxy(6,21); write('N:o ?'); gotoxy(13,21);
           readln(k1); val (k1,numero,code); until code=0;
           testnro; repeat gotoxy(6,22); write('kpl ?'); gotoxy(13,22);
           readln(k1); val (k1,kpl,code); until code=0; testkpl;
           testmarkkina1;
           markkina[numero]:=markkina[numero]-kpl;
           osakemaara[x,numero]:=osakemaara[x,numero]+kpl;
           varat[x]:=varat[x]-arvo[numero]*kpl; tyhjenna; laskeompros; end;
 end;
end;

procedure testkm;
begin
 if (km<>'M') and (km<>'m') and (km<>'K') and (km<>'k')
 then begin
  gotoxy(6,21); writeln('           ');
  gotoxy(6,21); km:=readkey;
  testkm; end
end;

procedure testmnro;
var k10:string;
begin
 if (numero>12) or (numero<=0) or (osakemaara[x,mnro]=0) then
  begin gotoxy(6,22);
  writeln('Anteeksi v„„r„ numero'); delay(1000);
  gotoxy(6,22); writeln('                        ');
  repeat gotoxy(13,21); readln(k10);
  val(k10,mnro,code); until code=0; testmnro; end;
end;

procedure testmkpl;
var k11:string;
begin
 if (mkpl<0) or (osakemaara[x,mnro]<mkpl) then begin
  gotoxy(6,23); writeln('Eip„s myyd„ enemp„„ kuin omistetaan !!!');
  delay(1000); gotoxy(6,23);
  writeln('                                              ');
  repeat gotoxy(13,22); readln(k11);
  val(k11,mkpl,code); until code=0; testmkpl;
  end;
end;

procedure myynti;
var k2:string;
begin
 textcolor(lightred);
 gotoxy(6,20); write('M'); textcolor(white); write('yynti / ');
 textcolor(lightred); write('K'); textcolor(white); writeln('aikki');
 gotoxy(6,21); km:=readkey; testkm; 
 case km of
  'K','k': begin repeat
           gotoxy(6,21); write('N:o ?'); gotoxy(13,21); readln(k2);
           val(k2,mnro,code); until code=0;
           testmnro; varat[x]:=varat[x]+osakemaara[x,mnro]*arvo[mnro];
           markkina[mnro]:=markkina[mnro]+osakemaara[x,mnro];
           osakemaara[x,mnro]:=0; tyhjenna; laskeompros; end;
  'M','m': begin repeat
           gotoxy(6,21); write('N:o ?'); gotoxy(13,21); readln(k2);
           val(k2,mnro,code); until code=0;
           testmnro; repeat gotoxy(6,22); write('kpl ?'); gotoxy(13,22);
           readln(k2); val (k2,mkpl,code); until code=0; testmkpl;
           osakemaara[x,mnro]:=osakemaara[x,mnro]-mkpl;
           varat[x]:=varat[x]+arvo[mnro]*mkpl;
           markkina[mnro]:=markkina[mnro]+mkpl;
           tyhjenna; laskeompros; end;
 end;
end;

procedure korjaapvalinta;
begin
 gotoxy(9,17); writeln('  '); gotoxy(9,17);
end;

procedure testsumma;
var k12:string;
begin
 if (summa>varat[x]) or (summa<0) then begin gotoxy(9,21);
  writeln('Eip„s huijjata !!!'); delay(1000); for i:=20 to 21 do begin
  gotoxy(9,i); writeln('                  '); end; repeat gotoxy(9,20);
  readln(k12); val(k12,summa,code); until code=0; testsumma; end;
end;

procedure testsumma2;
var k13:string;
begin
 if (summa2>saldo[x]) or (summa2<0) then begin gotoxy(9,21);
  writeln('Eip„s huijjata !!!'); delay(1000); for i:=20 to 21 do begin
  gotoxy(9,i); writeln('                  '); end; repeat gotoxy(9,20);
  readln(k13); val(k13,summa2,code); until code=0; testsumma2; end;
end;

procedure pankki;
var pois:boolean;
    omaisuus:longint;
    k3:string;
begin
 repeat
 clrscr; pois:=false; textcolor(lightblue); gotoxy(7,3);
 writeln('Pankki   (pelaaja: ',nimi[x],'   varat: ',varat[x],'   saldo: ',
 saldo[x],')');
 gotoxy(7,4); writeln('Korkoprosentti = ',kpy);
 textcolor(12); gotoxy(9,7); write('N'); textcolor(lightgreen);
  writeln('osto'); gotoxy(9,9); textcolor(12); write('T');
  textcolor(lightgreen); writeln('alletus');
  gotoxy(9,11); write('T'); textcolor(12); write('i'); textcolor(10);
  write('li tyhj„ksi'); gotoxy(9,13); textcolor(12); write('O'); textcolor
  (10); writeln('maisuuden arviointi');
  gotoxy(9,15); textcolor(12); write('L');
  textcolor(lightgreen); writeln('opeta');
 textcolor(white); gotoxy(9,17);
 repeat pvalinta:=readkey; korjaapvalinta;
 until pvalinta in ['N','n','T','t','I','i','L','l','O','o'];
 case pvalinta of
 'L','l': pois:=true;
 'I','i': begin gotoxy(9,19);
           varat[x]:=varat[x]+saldo[x]; saldo[x]:=0;
           writeln('Tilisi on nyt tyhj„!   Paina jotain n„pp„int„.');
           merkki:=readkey; end;
 'T','t': begin
           repeat gotoxy(9,19); writeln('Summa ?'); gotoxy(9,20);
           readln(k3); val(k3,summa,code); until code=0;
           testsumma; saldo[x]:=saldo[x]+summa;
           varat[x]:=varat[x]-summa; gotoxy(9,20);
           writeln('Saldo ',saldo[x],' mk.  Varat ',varat[x],' mk.');
           merkki:=readkey; end;
 'N','n': begin repeat
           gotoxy(9,19); writeln('Summa ?'); gotoxy(9,20);
           readln(k3); val(k3,summa2,code); until code=0;
           testsumma2; saldo[x]:=saldo[x]-summa2;
           varat[x]:=varat[x]+summa2; gotoxy(9,21);
           writeln('Saldo ',saldo[x],' mk.  Varat ',varat[x],' mk.');
           merkki:=readkey; end;
 'O','o': begin omaisuus:=varat[x]+saldo[x];
          for i:=1 to 12 do omaisuus:=omaisuus+osakemaara[x,i]*arvo[i];
          gotoxy(9,19);
          writeln('Omaisuutesi (varat+tilisi saldo+osakep„„omasi) = '
           ,omaisuus); merkki:=readkey; end
 end;
 until pois=true;
end;

procedure osakeporssi;
var cx,xt,yt:byte;
    rt:string;
    nt:char;
    zt,vt:longint;
    vex:boolean;
begin
 clrscr;
 vex:=false;
 textcolor(lightgreen); gotoxy(25,2); writeln('Mustan p”rssin osakemarkkinat');
 for cx:=1 to plm do begin
 textcolor(yellow); gotoxy(5+cx*25-25,5);
  writeln(cx,'. ',nimi[cx],':n osakkeet'); end;
 for cx:=1 to plm do if cx<>x then begin
  for i:=1 to 12 do begin gotoxy(5+cx*25-25,7+i); textcolor(lightred);
  writeln(i:2,'  ',yhtionimi[i]:10,'  ',osakemaara[cx,i]:6); end; end;
 textcolor(lightcyan);
 gotoxy(5,21);
  write('Kenen osakkeista olet kiinnostunut ? (tai paina 0 lopettaaksesi) ');
  repeat rt:=readkey; val(rt,xt,code); until ((code=0) and (xt>=0) and
   (xt<plm+1) and (xt<>x));
 if xt=0 then vex:=true;
 if vex=false then begin
 tyhjenna;
 gotoxy(5,21); write('Osakkeen numero ?'); repeat
  gotoxy(23,21); readln(rt); val(rt,yt,code);
  until ((code=0) and (yt>0) and (yt<13));
  if osakemaara[xt,yt]=0 then begin gotoxy(5,22);
   writeln('Tyhj„st„ on paha nyhj„st„ !'); delay(1000); osakeporssi; end
 else begin
  tyhjenna;
  gotoxy(5,21); write('Kuinka monta kappaletta ?'); repeat
   gotoxy(31,21); readln(rt); val(rt,zt,code);
   until (code=0) and (zt>0) and (zt<=osakemaara[xt,yt]);
  tyhjenna;
  gotoxy(5,21);
   writeln('Osakkeiden arvo nykyisell„ kurssilla on ',zt*arvo[yt],' mk.');
   gotoxy(5,22); write('Paljonko tarjoat ?'); repeat
    gotoxy(24,22); readln(rt); val(rt,vt,code); until
     (code=0) and (vt>=0) and (vt<=varat[x]);
  tyhjenna;
  gotoxy(2,21);
  textcolor(lightblue);
  writeln (nimi[xt],','); textcolor(lightcyan);
  writeln(' hyv„ksytk” tarjouksen ',vt,' mk ',zt,' kpl:sta ',yhtionimi[yt],':n osakeita ?');
  write(' [k/e] ');
   repeat nt:=readkey; until nt in ['K','k','E','e'];
  if nt in ['E','e'] then osakeporssi
  else begin
    osakemaara[xt,yt]:=osakemaara[xt,yt]-zt;
    osakemaara[x,yt]:=osakemaara[x,yt]+zt;
    varat[xt]:=varat[xt]+vt;
    varat[x]:=varat[x]-vt;
    write(' Kauppa suoritettu. Paina jotain n„pp„int„.'); merkki:=readkey;
  end;
 end; end;
end;

procedure testvalinta;
begin
 gotoxy(65,20); writeln('  '); gotoxy(65,20);
end;

procedure ohjelma;
var onko:boolean;
begin
 onko:=false;
 textcolor(lightgreen); textbackground(black); clrscr;
 writeln(' Pelaaja: ',nimi[x],'      Varat: ',varat[x],' mk');
 gotoxy(70,1); writeln('Kierros ',kierros);
 textcolor(lightblue); gotoxy(3,5);
  writeln(' N:o     Yhti”         Muutos  Arvo    Kpl  /Markk.    %');
 textcolor(lightcyan); for i:=1 to 12 do begin
  gotoxy(4,6+i); writeln(i:2);
  gotoxy(12,6+i);
  writeln(yhtionimi[i]);
  gotoxy(28,6+i);
  if muutos[i]>0 then textcolor(lightgreen) else
  if muutos[i]<0 then textcolor(lightred) else textcolor(lightcyan);
  writeln(muutos[i]:3); textcolor(11); gotoxy(34,6+i); writeln(arvo[i]:3);
  gotoxy(41,6+i);
  if osakemaara[x,i]>0 then textcolor(yellow) else textcolor(11);
  writeln(osakemaara[x,i]:6,'/',markkina[i]);
  gotoxy(56,6+i);
  if ompros[x,i]=0 then textcolor(black) else
  if ompros[x,i]<50 then textcolor(lightred) else textcolor(lightgreen);
   writeln(ompros[x,i]:3:0);
  textcolor(lightcyan);
  end;
 gotoxy(65,7);
 textcolor(lightred); writeln('Valitse:'); gotoxy(65,10);
  textcolor(lightred); write('O'); textcolor(white); writeln('sto');
  gotoxy (65,12); textcolor(lightred); write('M'); textcolor(white);
  writeln('yynti'); gotoxy(65,14); textcolor(lightred); write('P');
  textcolor(white); writeln('ankki'); gotoxy(65,19); textcolor(lightred);
  write('L'); textcolor(white); writeln('opeta vuoro');
 if plm>1 then begin gotoxy(65,16); textcolor(white); write('M');
  textcolor(lightred); write('u'); textcolor(white);
  writeln('stan p”rssin '); gotoxy(65,17);
  writeln('osakkeet'); end;
 gotoxy(65,20); repeat valinta:=readkey; testvalinta;
 until valinta in ['O','o','M','m','P','p','L','l','U','u'];
 case valinta of
  'O','o': begin osto; ohjelma; end;
  'L','l': tyhja;
  'M','m': begin for i:=1 to 12 do if osakemaara[x,i]>0 then onko:=true;
           if onko=true then begin myynti; ohjelma; end else ohjelma; end;
  'P','p': begin pankki; ohjelma; end;
  'U','u': begin osakeporssi; ohjelma; end;
 end;
end;

procedure osingot;
var kohta      : array[1..3] of shortint;
    z          : integer;
    rivi       : array[1..3] of shortint;
    luku       : longint;
    onko_o     : array[1..3] of byte;
    yhteensa   : array[1..3] of longint;
    jakaja     : byte;
begin
 clrscr; textcolor(green); for z:=1 to plm do begin rivi[z]:=5;
  onko_o[z]:=0; yhteensa[z]:=0; end; kohta[1]:=3; kohta[2]:=28; kohta[3]:=53;
 gotoxy(7,1); writeln('Osingot'); textcolor(white);
 for z:=1 to plm do begin
  gotoxy(kohta[z],3); writeln(nimi[z]); end;
 textcolor(lightred);
 for z:=1 to plm do for i:=1 to 12 do if osakemaara[z,i]>0 then
  begin luku:=0; onko_o[z]:=1; gotoxy(kohta[z],rivi[z]);
  writeln(yhtionimi[i],':'); gotoxy(kohta[z]+13,rivi[z]);
  if ompros[z,i]<50 then jakaja:=100;
  if ompros[z,i]>=50 then jakaja:=80;
  if ompros[z,i]>=60 then jakaja:=60;
  if ompros[z,i]>=70 then jakaja:=50;
  if ompros[z,i]>=80 then jakaja:=40;
  if ompros[z,i]>=90 then jakaja:=30;
  if ompros[z,i]=100 then jakaja:=20;
  luku:=osakemaara[z,i]*(arvo[i]) div jakaja; varat[z]:=varat[z]+luku;
  writeln(luku:6,' mk'); rivi[z]:=rivi[z]+1; yhteensa[z]:=yhteensa[z]+luku;
 end;
 for z:=1 to plm do if onko_o[z]=0 then begin
  gotoxy(kohta[z],rivi[z]); writeln('Ei osinkoja.');
 end;
 for z:=1 to plm do begin
 if yhteensa[z]>0 then begin gotoxy(kohta[z],rivi[z]+1);
  writeln('Yhteens„ ',yhteensa[z],' mk'); end; end;
 gotoxy(27,22); writeln('PAINA JOTAIN NŽPPŽINTŽ');
 merkki:=readkey;
end;

procedure muutokset;
begin
 ptuki:=0;
 for i:=1 to 12 do begin
 muutos[i]:=random(61)-30; if muutos[i]<0 then ptuki:=ptuki+1;
 arvo[i]:=arvo[i]+muutos[i];
 if arvo[i]>=200 then begin clrscr; gotoxy(10,10); textcolor(lightred);
                      writeln('JEEEEEEEESSSSSSSS !!!!'); gotoxy(10,12);
                      writeln('Osakeanti. ',yhtionimi[i],' kaksinkertaistaa osakkeensa.');
                      arvo[i]:=100; for x:=1 to plm do
                      osakemaara[x,i]:=osakemaara[x,i]*2;
                      markkina[i]:=markkina[i]*2;
                      maxosake[i]:=maxosake[i]*2;
                      merkki:=readkey; end;
 if arvo[i]<=0 then   begin clrscr; gotoxy(10,10); textcolor(lightred);
                      writeln('No voihan per... !!'); gotoxy(10,12);
                      writeln(yhtionimi[i],' teki konkurssin.'); gotoxy(10,13);
                      writeln('Otan osaa... Menetitte osakkeenne.');
                      for x:=1 to plm do
                       if ompros[x,i]>=50 then begin
                       saldo[x]:=saldo[x]-((saldo[x] div 100)*85);
                       varat[x]:=varat[x]-((varat[x] div 100)*90);
                       gotoxy(10,14);
                       writeln('Yhti”n omistaja ',nimi[x],' menett„„ konkurssin');
                       gotoxy(10,15); writeln('yhteydess„ 85% saldostaan sek„');
                       gotoxy(10,16); writeln('90% k„teisvaroistaan.'); end;
                      arvo[i]:=100; for x:=1 to plm do begin osakemaara[x,i]:=0;
                      ompros[x,i]:=0; end; maxosake[i]:=(maxosake[i] div 2);
                      if maxosake[i]<50000 then maxosake[i]:=50000;
                      markkina[i]:=maxosake[i];
                      merkki:=readkey; end;
 end; 
end;

procedure pankkikonkka;
var kpros:integer;
begin
  kpros:=0;
  if ptuki>7 then begin
   kpros:=random(21)+50;
   for i:=1 to plm do saldo[i]:=saldo[i]-(kpros*saldo[i] div 100);
   clrscr; textcolor(lightred); gotoxy(10,8);
   writeln('Tiedotus kaikille pelaajille:');
   textcolor(lightgreen); gotoxy(10,11);
   writeln('Laskusuhdanteen johdosta pankkimme on perinyt tileilt„nne');
   gotoxy(10,12); writeln(kpros,' prosenttia saldoistanne pankkitukena.');
   gotoxy(10,13); writeln('Toivomme teid„n silti s„ilytt„v„n luottamuksenne');
   gotoxy(10,14); writeln('pankkimme vakavaraisuuteen ja maksukykyyn.');
   gotoxy(10,15); writeln('Pahoittelumme osoituksena talletustenne korkoa');
   gotoxy(10,16); writeln('on nostettu yhdell„ prosenttiyksik”ll„.');
   gotoxy(10,18); textcolor(white); writeln('Paina jotain n„pp„int„.');
   merkki:=readkey;
   end
end;

procedure korot;
begin
 if ptuki>7 then kpy:=kpy+1;
 for x:=1 to plm do
  if saldo[x]>0 then saldo[x]:=saldo[x]+(((saldo[x]) div 100)*kpy);
end;

procedure testaalopetus;
var y:byte;
var z:array[1..3] of byte;
    paaoma:array[1..3] of longint;
begin
 for y:=1 to plm do z[y]:=0;
 for y:=1 to plm do for i:=1 to 12 do
  if ompros[y,i]>50 then z[y]:=z[y]+1;
 for y:=1 to plm do if z[y]>=7 then varma:='k';
 for y:=1 to plm do for i:=1 to 12 do begin
 paaoma[y]:=varat[y]+saldo[y]; paaoma[y]:=paaoma[y]+osakemaara[y,i]*arvo[i];
 if paaoma[y]>=2000000000 then varma:='k';
end;
end;

procedure lopetammeko;
begin
if varma='e' then begin
clrscr; textcolor(lightgray);
gotoxy (10,10); writeln('L=lopetus, muulla n„pp„imell„ peli jatkuu.');
gotoxy(10,11); lk:=readkey; if (lk='L') or (lk='l') then begin repeat
 gotoxy(10,11); writeln('Oletko varma ? [K/E] '); gotoxy(10,12);
 readln(varma); until varma in ['K','k','E','e'];
 case valinta of
      'E','e': lopetammeko;
      end end
 end
end;

procedure lopetus;
begin
 kierros:=kierros-1;
 clrscr; textcolor(lightgray);
 gotoxy(1,8); writeln('Game Over. (kierroksia ',kierros,')');
 gotoxy(1,10);
 if plm=1 then begin varat[1]:=varat[1]+saldo[1];
  for i:=1 to 12 do varat[1]:=varat[1]+osakemaara[1,i]*arvo[i];
  writeln('Varat : ',varat[1]);  end;
 if plm=2 then begin
  for x:=1 to 2 do begin varat[x]:=varat[x]+saldo[x];
   for i:=1 to 12 do varat[x]:=varat[x]+(osakemaara[x,i]*arvo[i]);
  end;
  writeln('Pelaajan 1 varat: ',varat[1]);
  writeln('Pelaajan 2 varat: ',varat[2]);
  writeln; writeln;
  if varat[1]=varat[2] then writeln('Tasapeli !!!!');
  if varat[1]>varat[2] then writeln(nimi[1],' oli (t„ll„ kertaa) parempi');
  if varat[1]<varat[2] then writeln(nimi[2],' oli (t„ll„ kertaa) parempi');
 end;
 if plm=3 then begin
  for x:=1 to 3 do begin
   varat[x]:=varat[x]+saldo[x];
   for i:=1 to 12 do varat[x]:=varat[x]+(osakemaara[x,i]*arvo[i]); end;
  for x:=1 to 3 do 
   writeln('Pelaajan ',x,' varat : ',varat[x]);
  writeln;writeln;
  if varat[1]=varat[2] then if varat[1]=varat[3] then writeln('Tasapeli !!!');
  if (varat[1]=varat[2]) and (varat[1]>varat[3]) then
   writeln(nimi[1],' ja ',nimi[2],' voittivat tasatuloksella');
  if (varat[1]=varat[3]) and (varat[1]>varat[2]) then
   writeln(nimi[1],' ja ',nimi[3],' voittivat tasatuloksella');
  if (varat[2]=varat[3]) and (varat[2]>varat[1]) then
   writeln(nimi[2],' ja ',nimi[3],' voittivat tasatuloksella');
  if varat[1]>varat[2] then if varat[1]>varat[3] then
   writeln(nimi[1],' oli (t„ll„ kertaa) paras.');
  if varat[2]>varat[1] then if varat[2]>varat[3] then
   writeln(nimi[2],' oli (t„ll„ kertaa) paras.');
  if varat[3]>varat[1] then if varat[3]>varat[2] then
   writeln(nimi[3],' oli (t„ll„ kertaa) paras.');
 end;
end;

begin
{randomize;}
alkuvalikko;
{lukumaara;
aseta;
lue_nimet;
lue_yhtiot;
repeat
 for x:=1 to plm do ohjelma;
 osingot;
 muutokset;
 pankkikonkka;
 korot;
 kierros:=kierros+1;
 testaalopetus;
 lopetammeko;
until varma in ['K','k'];
lopetus;}
end.



