class ListFunctions
{
    fun <T,R> map(list : List<T>, f : (T) -> R) : List<R> = when(list)
    {
        is List.Nil -> List.Nil as List<R>
        is List.Node -> List.Node(f(list.head), map(list.tail, f))
    }

    fun <T> replaceIf(list : List<T>, f : (T) -> T, p : (T) -> Boolean) : List<T> = when(list)
    {
        is List.Nil -> List.Nil as List<T>
        is List.Node -> List.Node(if(p(list.head)) f(list.head) else list.head, replaceIf(list.tail, f, p) )
    }

    fun <T> filter(list : List<T>, p : (T) -> Boolean) : List<T> = when(list)
    {
        is List.Nil -> List.Nil as List<T>
        is List.Node -> {
            if(!p(list.head))
                List.Node(list.head, filter(list.tail, p))
            else
                filter(list.tail, p)
        }
    }

    fun <T> any(list : List<T>, p : (T) -> Boolean) : Boolean = when(list)
    {
        is List.Nil -> false
        is List.Node -> p(list.head) || any(list.tail, p)
    }

    fun <T,R> recursiveFold(list : List<T>, accumulated : R, f : (R, T) -> R) : R = when(list)
    {
        is List.Nil -> accumulated
        is List.Node -> recursiveFold(list.tail, f(accumulated, list.head), f)
    }

    fun <T,R> iterativeFold(list : List<T>, accumulated : R, f : (R, T) -> R) : R
    {
        var tmp = list
        var acc = accumulated
        while(tmp is List.Node)
        {
            acc = f(accumulated, tmp.head)
            tmp = tmp.tail

        }
        return acc
    }

    fun <T,R> foldedFold(list : List<T>, accumulated : R, f : (R, T) -> R) : R
    {
        val listToBeFolded = arrayListOf<T>()
        var tmp = list
        while(tmp is List.Node)
        {
            listToBeFolded.add(tmp.head)
            tmp = tmp.tail
        }
        return listToBeFolded.fold(accumulated, f)
    }
}
