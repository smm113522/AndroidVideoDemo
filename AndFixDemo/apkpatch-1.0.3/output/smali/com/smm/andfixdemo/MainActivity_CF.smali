.class public Lcom/smm/andfixdemo/MainActivity_CF;
.super Landroid/support/v7/app/AppCompatActivity;
.source "MainActivity.java"


# static fields
.field private static final APATCH_PATH:Ljava/lang/String; = "/fix.apatch"


# instance fields
.field private btandfix:Landroid/widget/Button;

.field private bttomast:Landroid/widget/Button;

.field private tvtishi:Landroid/widget/TextView;


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 15
    invoke-direct {p0}, Landroid/support/v7/app/AppCompatActivity;-><init>()V

    return-void
.end method

.method static synthetic access$000(Lcom/smm/andfixdemo/MainActivity;)V
    .locals 0
    .param p0, "x0"    # Lcom/smm/andfixdemo/MainActivity;

    .prologue
    .line 15
    invoke-direct {p0}, Lcom/smm/andfixdemo/MainActivity_CF;->update()V

    return-void
.end method

.method static synthetic access$100(Lcom/smm/andfixdemo/MainActivity;)V
    .locals 0
    .param p0, "x0"    # Lcom/smm/andfixdemo/MainActivity;
    .annotation runtime Lcom/alipay/euler/andfix/annotation/MethodReplace;
        clazz = "com.smm.andfixdemo.MainActivity"
        method = "access$100"
    .end annotation

    .prologue
    .line 15
    invoke-direct {p0}, Lcom/smm/andfixdemo/MainActivity_CF;->showToast1()V

    return-void
.end method

.method private showToast()V
    .locals 2

    .prologue
    .line 46
    const-string v0, "\u6253\u8865\u4e01\u4e4b\u524d"

    const/4 v1, 0x1

    invoke-static {p0, v0, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 47
    return-void
.end method

.method private showToast1()V
    .locals 2

    .prologue
    .line 49
    const-string v0, "\u6253\u8865\u4e01\u4e4b\u540e"

    const/4 v1, 0x1

    invoke-static {p0, v0, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 50
    iget-object v0, p0, Lcom/smm/andfixdemo/MainActivity_CF;->tvtishi:Landroid/widget/TextView;

    const-string v1, "\u6211\u4eec\u90fd\u662f\u597d\u5b69\u5b50"

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 51
    return-void
.end method

.method private update()V
    .locals 6

    .prologue
    .line 53
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-static {}, Landroid/os/Environment;->getExternalStorageDirectory()Ljava/io/File;

    move-result-object v4

    invoke-virtual {v4}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, "/fix.apatch"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    .line 55
    .local v2, "patchFileStr":Ljava/lang/String;
    new-instance v1, Ljava/io/File;

    invoke-direct {v1, v2}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 57
    .local v1, "file":Ljava/io/File;
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v3

    if-eqz v3, :cond_0

    .line 61
    :cond_0
    invoke-virtual {v1}, Ljava/io/File;->isDirectory()Z

    move-result v3

    if-eqz v3, :cond_1

    .line 62
    invoke-virtual {p0}, Lcom/smm/andfixdemo/MainActivity_CF;->getApplicationContext()Landroid/content/Context;

    move-result-object v3

    const-string v4, "\u5b58\u5728"

    const/4 v5, 0x0

    invoke-static {v3, v4, v5}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v3

    invoke-virtual {v3}, Landroid/widget/Toast;->show()V

    .line 66
    :cond_1
    :try_start_0
    const-string v3, "smm"

    invoke-static {v3, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 67
    sget-object v3, Lcom/smm/andfixdemo/AndFixApplication;->mPatchManager:Lcom/alipay/euler/andfix/patch/PatchManager;

    invoke-virtual {v3, v2}, Lcom/alipay/euler/andfix/patch/PatchManager;->addPatch(Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 72
    :goto_0
    return-void

    .line 68
    :catch_0
    move-exception v0

    .line 69
    .local v0, "e":Ljava/io/IOException;
    const-string v3, "smm1"

    invoke-virtual {v0}, Ljava/io/IOException;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 70
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    goto :goto_0
.end method


# virtual methods
.method protected onCreate(Landroid/os/Bundle;)V
    .locals 2
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    .line 25
    invoke-super {p0, p1}, Landroid/support/v7/app/AppCompatActivity;->onCreate(Landroid/os/Bundle;)V

    .line 26
    const v0, 0x7f04001b

    invoke-virtual {p0, v0}, Lcom/smm/andfixdemo/MainActivity_CF;->setContentView(I)V

    .line 27
    const v0, 0x7f0b0060

    invoke-virtual {p0, v0}, Lcom/smm/andfixdemo/MainActivity_CF;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/Button;

    iput-object v0, p0, Lcom/smm/andfixdemo/MainActivity_CF;->bttomast:Landroid/widget/Button;

    .line 28
    const v0, 0x7f0b005f

    invoke-virtual {p0, v0}, Lcom/smm/andfixdemo/MainActivity_CF;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/Button;

    iput-object v0, p0, Lcom/smm/andfixdemo/MainActivity_CF;->btandfix:Landroid/widget/Button;

    .line 29
    const v0, 0x7f0b005e

    invoke-virtual {p0, v0}, Lcom/smm/andfixdemo/MainActivity_CF;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/smm/andfixdemo/MainActivity_CF;->tvtishi:Landroid/widget/TextView;

    .line 31
    iget-object v0, p0, Lcom/smm/andfixdemo/MainActivity_CF;->btandfix:Landroid/widget/Button;

    new-instance v1, Lcom/smm/andfixdemo/MainActivity$1;

    invoke-direct {v1, p0}, Lcom/smm/andfixdemo/MainActivity$1;-><init>(Lcom/smm/andfixdemo/MainActivity;)V

    invoke-virtual {v0, v1}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 38
    iget-object v0, p0, Lcom/smm/andfixdemo/MainActivity_CF;->bttomast:Landroid/widget/Button;

    new-instance v1, Lcom/smm/andfixdemo/MainActivity$2;

    invoke-direct {v1, p0}, Lcom/smm/andfixdemo/MainActivity$2;-><init>(Lcom/smm/andfixdemo/MainActivity;)V

    invoke-virtual {v0, v1}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 44
    return-void
.end method
